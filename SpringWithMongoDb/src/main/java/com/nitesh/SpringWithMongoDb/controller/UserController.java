package com.nitesh.SpringWithMongoDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.SpringWithMongoDb.model.User;
import com.nitesh.SpringWithMongoDb.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public String placeOrder(@RequestBody User user) {
		repository.save(user);
		return "Order placed successfully....";
	}
	
	@GetMapping("/{name}")
	public List<User> getUserByName(@PathVariable String name){
		return repository.findByName(name);
	}
	
	@GetMapping("/")
	public List<User> findAllUser(){
		return repository.findAll();
	}
	
	
	@GetMapping("/address/{city}")
	public List<User> getUserByAddressCity(@PathVariable String city){
		return repository.findByCity(city);
	}
	
}
