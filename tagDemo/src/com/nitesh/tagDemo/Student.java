package com.nitesh.tagDemo;

public class Student {

	private String firstName,lastName;
	private Boolean joinCompany;
	public Student(String firstName, String lastName, Boolean joinCompany) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinCompany = joinCompany;
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
	public Boolean getJoinCompany() {
		return joinCompany;
	}
	public void setJoinCompany(Boolean joinCompany) {
		this.joinCompany = joinCompany;
	}
	
	
}
