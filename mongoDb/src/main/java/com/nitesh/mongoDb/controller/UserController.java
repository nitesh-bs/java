package com.nitesh.mongoDb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.mongoDb.User;
import com.nitesh.mongoDb.UserDetails;
import com.nitesh.mongoDb.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping("/user")
	private List<User> getAllUser() {
		return userService.findAll();
	}

	@PostMapping("/user")
	private void saveUser() {
		List<String> list = new ArrayList<>();
		list.add("ssd");
		list.add("ssdsdds");

		User user2 = new User( "apvs@gmail.com", "abc", new UserDetails("abcc", "ind"), list);

		/*
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where("email").is(user2.getEmail())); List<User>
		 * user = mongoTemplate.find(query, User.class);
		 * 
		 * if(user.size() >= 1) { throw new
		 * RuntimeException("Found users with this email"); } if(user.isEmpty()) {
		 * userRepository.insert(user2); System.out.println("Inserted user : "+user2); }
		 * else {
		 * 
		 * System.out.println("Already exists user email : "+user2.getEmail()+
		 * " : "+user.size()); }
		 */
		
		userService.saveUser(user2);
		
	}
}
