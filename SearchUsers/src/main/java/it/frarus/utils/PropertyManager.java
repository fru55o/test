package it.frarus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	public static final String SYSTEM_PROPERTY = "/META-INF/searchUsers.properties";
	public static final String PROVIDER_URL = "provider_url";
	public static final String SECURITY_PRINCIPAL = "security_principal";
	public static final String SECURITY_CREDENTIALS = "security_credentials";
	
	private String provider_url;
	private String security_principal;
	private String security_credentials;

	private static PropertyManager _instance = new PropertyManager();

	public static PropertyManager instance() {
		return _instance;
	}

	public PropertyManager() {
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws Exception {
		Properties props = new Properties();
		InputStream is = getClass().getResourceAsStream(SYSTEM_PROPERTY);
		try {
			if (is != null) {
				props.load(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		provider_url = props.getProperty(PROVIDER_URL, "provider_url");
		security_principal = props.getProperty(SECURITY_PRINCIPAL, "security_principal");
		security_credentials = props.getProperty(SECURITY_CREDENTIALS, "security_credentials");
	}

	public String getProviderUrl() {
		return provider_url;
	}

	public String getSecurityPrincipal() {
		return security_principal;
	}

	public String getSecurityCredentials() {
		return security_credentials;
	}

}
