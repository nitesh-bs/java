package com.nitesh.mongoDb;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String email;
	private String password;
	private UserDetails userDetails;
	private List<String> favouriteGames;
	
	
	
	public User() {
		
	}

	public User(String id, String email, String password, UserDetails userDetails, List<String> favouriteGames) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.userDetails = userDetails;
		this.favouriteGames = favouriteGames;
	}

	public User(String email, String password, UserDetails userDetails, List<String> favouriteGames) {
		this.email = email;
		this.password = password;
		this.userDetails = userDetails;
		this.favouriteGames = favouriteGames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public List<String> getFavouriteGames() {
		return favouriteGames;
	}
	public void setFavouriteGames(List<String> favouriteGames) {
		this.favouriteGames = favouriteGames;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", userDetails=" + userDetails + ", favouriteGames="
				+ favouriteGames + "]";
	}
	
	
}
