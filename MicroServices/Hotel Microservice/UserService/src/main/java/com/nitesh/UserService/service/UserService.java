package com.nitesh.UserService.service;

import java.util.List;

import com.nitesh.UserService.entity.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	//TODO: update,delete
}
