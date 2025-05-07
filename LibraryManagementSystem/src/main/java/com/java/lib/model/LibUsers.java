package com.java.lib.model;

public class LibUsers {
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LibUsers(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public LibUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
