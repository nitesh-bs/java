package com.nitesh.restwebservices.controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nitesh.restwebservices.bean.User;
import com.nitesh.restwebservices.dao.UserDao;
import com.nitesh.restwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return userDao.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public EntityModel<User> findOneUser(@PathVariable int userId) {
		User findOne = userDao.findOne(userId);
		if(findOne == null) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		
		EntityModel<User> entityModel = EntityModel.of(findOne);
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).findAllUsers());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		 User savedUser = userDao.addUser(user);
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{userId}")
				 .buildAndExpand(savedUser.getId())
				 .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		User findOne = userDao.findOne(userId);
		if(findOne == null) {
			throw new UserNotFoundException("User not found id - "+userId);
		}
		userDao.deleteById(userId);
		
	}
}
