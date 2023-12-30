package com.nitesh.mongoDb;

public class UserDetails {
	private String username;
	private String country;
	
	
	
	public UserDetails() {
		
	}
	public UserDetails(String username, String country) {
		this.username = username;
		this.country = country;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", country=" + country + "]";
	}
	
	
}
