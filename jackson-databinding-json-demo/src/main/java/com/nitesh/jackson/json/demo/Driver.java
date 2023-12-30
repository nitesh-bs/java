package com.nitesh.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			Student student = mapper.readValue(new File("data/sample-full.json"),	 Student.class);
			
			System.out.println("student : "+student.getFirstName()+ " "+student.getLastName());
			
			System.out.println("student Address : "+student.getAddress().getCity());
			
			System.out.println("student languages");
			for(String temp:student.getLanguages()) {
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
