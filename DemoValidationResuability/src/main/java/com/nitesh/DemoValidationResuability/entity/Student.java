package com.nitesh.DemoValidationResuability.entity;


import com.nitesh.DemoValidationResuability.validation.ValidEmail;


public class Student {

	@jakarta.validation.constraints.NotBlank(message = "Username not be blank.")
	@jakarta.validation.constraints.NotNull(message = "Username must be required!")
	private String firstName;
	
	private String lastName;
	
	@ValidEmail
	private String email;
	
	
	public Student() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
	
}
