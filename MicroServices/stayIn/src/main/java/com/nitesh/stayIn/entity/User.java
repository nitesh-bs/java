package com.nitesh.stayIn.entity;

import java.util.List;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
import com.nitesh.stayIn.view.View;

@Entity
@Table(name = "userMaster")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	@JsonView(View.UserView.class)
	private Integer userId;

	@Column(name = "username")
	@JsonView(View.UserView.class)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;

	@Column(name = "userType")
	@JsonView(View.UserView.class)
	private String userType;

	@Column(name = "userStatus")
	@JsonView(View.UserView.class)
	private String userStatus;

	@Transient
	@JsonInclude(value = Include.NON_NULL)
	@JsonView(View.UserView.class)
	private String userToken;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	@JsonView(View.RentMasterWithUserView.class)
	private List<RentMaster> rentMaster;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	@JsonView(View.UserDetailsWithBookingView.class)
	private List<Booking> bookings;

	@OneToOne(mappedBy = "user")
	@JsonManagedReference
	@JsonView(View.UserDetailsWithUserView.class)
	private UserDetails userDetails;

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String userType, String userStatus) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.userStatus = userStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", userType=" + userType
				+ ", userStatus=" + userStatus + ", userToken=" + userToken + "]";
	}

}
