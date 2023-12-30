package com.nitesh.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.UserService.entity.User;
import com.nitesh.UserService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user){
		User response = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	int retryCount=1;
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback" )
//	@Retry(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback" )
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<?> getSingleUser(@PathVariable String userId){
		System.out.println("Retry Count : "+ retryCount);
		retryCount++;
		return ResponseEntity.status(200).body(userService.getUser(userId));
	}

	//fallback method for circuitBreaker
	//need same return type of method and same parameter for circuite breaker
	public ResponseEntity<?> ratingHotelFallback(String userId,Exception ex){
		
		System.out.println("Fallback : "+ex.getMessage());
		return ResponseEntity.ok("ratingHotelFallback Called");
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllUser(){
		return ResponseEntity.status(200).body(userService.getAllUser());
	}
}
