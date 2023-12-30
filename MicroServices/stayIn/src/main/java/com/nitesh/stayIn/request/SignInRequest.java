package com.nitesh.stayIn.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SignInRequest {

	@NotBlank(message = "Username not be blank.")
	@NotNull(message = "Username must be required!")
	private String username;

	@NotBlank(message = "Password not be blank.")
	@NotNull(message = "Password must be required!")
	private String password;

	public SignInRequest() {
	}

	public SignInRequest(
			@NotBlank(message = "Username not be blank.") @NotNull(message = "Username must be required!") String username,
			@NotBlank(message = "Password not be blank.") @NotNull(message = "Password must be required!") String password) {
		this.username = username;
		this.password = password;
	}

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

	@Override
	public String toString() {
		return "SignInRequest [username=" + username + ", password=" + password + "]";
	}

}
