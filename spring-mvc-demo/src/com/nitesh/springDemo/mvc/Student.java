package com.nitesh.springDemo.mvc;

import java.util.LinkedHashMap;

public class Student {

	private String firstName;
	private String lastName;
	private String country;
	private String favLang;
	private String[] operatingSystem;
	

	private LinkedHashMap<String, String> countryOptions;
	 private LinkedHashMap<String, String> favoriteLanguageOptions;
	
	public Student() {
		countryOptions = new LinkedHashMap<>();
		
		countryOptions.put("in", "India");
		countryOptions.put("fr", "France");
		countryOptions.put("de", "Germany");
		countryOptions.put("us", "US");
		
        favoriteLanguageOptions = new LinkedHashMap<>();
       
        favoriteLanguageOptions.put("Java", "Java");
        favoriteLanguageOptions.put("C#", "C#");
        favoriteLanguageOptions.put("PHP", "PHP");
        favoriteLanguageOptions.put("Ruby", "Ruby");  
		
	}

	public LinkedHashMap<String, String> getCountryOptions() {
		return countryOptions;
	}

	public Student(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFavLang() {
		return favLang;
	}

	public void setFavLang(String favLang) {
		this.favLang = favLang;
	}
	
    public LinkedHashMap<String, String> getFavoriteLanguageOptions() {
        return favoriteLanguageOptions;
    }	
	
    public String[] getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String[] operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	
}
