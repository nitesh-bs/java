package com.nitesh.springBootTestCase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.springBootTestCase.entity.Person;
import com.nitesh.springBootTestCase.repository.PersonRepo;

@Service
public class PersonService {

	
	private PersonRepo personRepo;

	public List<Person> getAllPerson() {
		return personRepo.findAll();
	}
	
	@Autowired
	public PersonService(PersonRepo repo) {
		// this keyword refers to current instance
		this.personRepo = repo;
	}
	
	public Optional<Person> findByPersonId(int id) {
		return personRepo.findById(id);
	}
}