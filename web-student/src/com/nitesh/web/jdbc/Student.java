package com.nitesh.web.jdbc;

public class Student {

	private int studId;
	private String firstName, lastName, email;

	public Student(int studId, String firstName, String lastName, String email) {
		super();
		this.studId = studId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

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
		return "Student [studId=" + studId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}



	

}
