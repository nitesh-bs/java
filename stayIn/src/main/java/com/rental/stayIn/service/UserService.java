package com.rental.stayIn.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rental.stayIn.entity.User;

public interface UserService{

	List<User> findAllUser();
	
	User findByUsername(String username);
	
	User registerUser(User user);
}
