package com.nitesh.springBootJwt.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springBootJwt.entity.User;
import com.nitesh.springBootJwt.response.LoginResponse;
import com.nitesh.springBootJwt.response.OkResponse;
import com.nitesh.springBootJwt.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody User user) {
		
		LoginResponse loginResponse = userService.signIn(user);
		return ResponseEntity.ok(new OkResponse("User logging Successfully", loginResponse));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody User user) {
		User createdUser = userService.registerUser(user);
		return ResponseEntity.ok(new OkResponse("User Created Successfully", createdUser));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> home(Principal principal){
		return ResponseEntity.ok(new OkResponse("Home"
				+principal.getName(),null));
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> forAdmin(){
		return ResponseEntity.ok(new OkResponse("Admin",null));
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> forUser(){
		return ResponseEntity.ok(new OkResponse("User",null));
	}
}
