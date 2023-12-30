package com.rental.stayIn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rental.stayIn.entity.User;
import com.rental.stayIn.service.UserService;

@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<?> login(){
		
		return ResponseEntity.ok("Login successfull");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		System.out.println(user);
		
		
		if(user.getUserType().equals("ROLE_A")) {
			user.setUserStatus("A");
		}
		else if (user.getUserType().equals("ROLE_O")) {
			user.setUserStatus("V");
		}else {
			user.setUserStatus("V");
		}
		
		
		userService.registerUser(user);
		return ResponseEntity.ok("register successfull");
	}
}
