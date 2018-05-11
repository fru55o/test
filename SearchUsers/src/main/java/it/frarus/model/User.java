package it.frarus.model;

public class User {
	
	public User () {
		
	}
	
	public User (String uid, String name, String surname) {
		this.uid = uid;
		this.name = name;
		this.surname = surname;
	}
	
	
	private String uid;
	private String name;
	private String surname;
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	
}
