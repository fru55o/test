package it.frarus.ldap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import it.frarus.model.User;
import it.frarus.utils.PropertyManager;

public class LdapSearch {
	private Hashtable<String, String> env;
	private DirContext ctx;
	
	public LdapSearch () {
		createConnection();
	}
	
	private void createConnection () {
		try {
			boolean contextCreated = ctx != null;
			if(!contextCreated) {
				String providerUrl = PropertyManager.instance().getProviderUrl();
				String securityCredentials = PropertyManager.instance().getSecurityCredentials();
				String securityPrincipal = PropertyManager.instance().getSecurityPrincipal();
				// Set up the environment for creating the initial context
				env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.PROVIDER_URL, providerUrl);
				//
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, securityCredentials); 
				env.put(Context.SECURITY_CREDENTIALS, securityPrincipal);
				
				// Create the initial context
				ctx = new InitialDirContext(env);
			}
			
		} catch (NamingException e) {
			System.err.println(e);
		}
	}
	
	private void closeConnection () {
		try {
			boolean contextCreated = ctx != null;
			if(contextCreated) {
				ctx.close();
			}
		} catch (NamingException e) {
			System.err.println(e);
		}
	}
	
	public Collection searchUser(String username, String password) {
		Collection <User> result = new ArrayList<User>();
				try {
					String base = "dc=myorg,dc=test";
					SearchControls sc = new SearchControls();
					String[] attributeFilter = { "uid", "userpassword", "name", "surname"};
					sc.setReturningAttributes(attributeFilter);
					sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

					String filter = "(&(uid=" + username +")&(userpassword=" + password + "))";

					NamingEnumeration results = ctx.search(base, filter, sc);
					System.out.println("Search....");
					int counter = 0;
					while (results.hasMore()) {
						if(counter==0) {
							SearchResult sr = (SearchResult) results.next();
							User user = new User();
							Attributes attrs = sr.getAttributes();
							
							user.setUid(username);
							
							Attribute attr = attrs.get("cn");
							String name = attr.get().toString();
							user.setName(name);
							
							Attribute attr2 = attrs.get("sn");
							String surname = attr2.get().toString();
							user.setSurname(surname);
							
							result.add(user);
							counter ++;
						}
					}

				} catch (NamingException e) {
					System.err.println(e);
				} finally {
					closeConnection();
				}
		
		return result;
	}
}
