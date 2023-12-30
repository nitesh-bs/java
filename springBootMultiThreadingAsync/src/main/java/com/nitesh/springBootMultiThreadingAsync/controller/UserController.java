package com.nitesh.springBootMultiThreadingAsync.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nitesh.springBootMultiThreadingAsync.entity.User;
import com.nitesh.springBootMultiThreadingAsync.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping(value = "/users",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})  
	public ResponseEntity<?> saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception{
		for(MultipartFile file:files) {
			service.saveUser(file);
		}
		return ResponseEntity.status(200).body("Inserted Successfully");
	}
	
	@GetMapping("/getAllUsers")
	public CompletableFuture<ResponseEntity> findAllUsers(){
		return service.findAllUsers().thenApply(ResponseEntity::ok);
	}
	
	@GetMapping("/getUsersByThread")
	public  ResponseEntity<?> getUsers(){
		CompletableFuture<List<User>> user1=service.findAllUsers();
		CompletableFuture<List<User>> user2=service.findAllUsers();
		CompletableFuture<List<User>> user3=service.findAllUsers();
		CompletableFuture.allOf(user1,user2,user3).join();
		return ResponseEntity.status(200).build();
	}
}
