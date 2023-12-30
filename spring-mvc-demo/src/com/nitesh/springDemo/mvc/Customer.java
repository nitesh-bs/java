package com.nitesh.springDemo.mvc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

import com.nitesh.springDemo.mvc.validation.CourseCode;

public class Customer {

	@NotNull(message = "fristName is required!")
	private String firstName;
	
	@NotNull(message = "lastName is required!")
	@Size(min = 1,message = "Last Name is not be empty!")
	private String lastName;
	
	@NotNull(message = "is required!")
	@Min(value = 0,message = "FreePass must be grater or equal 0")
	@Max(value = 10,message = "FreePass must be lesser or equal 10")
	private Integer freePass;
	
	@NotNull(message = "Postal Code is required!")
	@Pattern(regexp = "^[a-z0-9]{6}",message = "Postal Code must be 6 digit")
	private String postCode;

	@NotNull(message = "Cource Code is required!")
	@CourseCode(value={"nit","tops"},message="must start with nit or tops")
	private String courceCode;
	
	
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

	public Integer getFreePass() {
		return freePass;
	}

	public void setFreePass(Integer freePass) {
		this.freePass = freePass;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCourceCode() {
		return courceCode;
	}

	public void setCourceCode(String courceCode) {
		this.courceCode = courceCode;
	}
	
	
	
}
