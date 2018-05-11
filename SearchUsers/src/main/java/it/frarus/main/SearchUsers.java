package it.frarus.main;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import it.frarus.ldap.LdapSearch;
import it.frarus.model.User;

public class SearchUsers {

	public static void main(String[] args) {
		System.out.println("Start SearchUsers");
		
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		
		System.out.println("Enter your password: ");
		scanner = new Scanner(System.in);
		String password = scanner.nextLine();
		
		if(username==null&&password==null) {
			System.err.println("Usage: SearchUsers accetta username e password in input");
			System.exit(0);
		}else {
			if(username.length()==0) {
				System.err.println("Usage: SearchUsers accetta username e password in input");
				System.exit(0);
			}
			
			if(password.length()==0) {
				System.err.println("Usage: SearchUsers accetta username e password in input");
				System.exit(0);
			}
		}
		
		LdapSearch ls = new LdapSearch();
		Collection searchUser = ls.searchUser(username, password);
		if(searchUser.size()>0) {
			System.out.println("Welcome!");
			for (Iterator iterator = searchUser.iterator(); iterator.hasNext();) {
				User object = (User) iterator.next();
				System.out.println("Name: " + object.getName());
				System.out.println("Surname: " + object.getSurname());
			}
		}else {
			System.out.println("User not found!");
		}
		System.out.println("End SearchUsers");
	}

}
