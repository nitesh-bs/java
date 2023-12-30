package com.nitesh.springBootJwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nitesh.springBootJwt.entity.User;
import com.nitesh.springBootJwt.response.LoginResponse;

public interface UserService extends UserDetailsService{

	User registerUser(User user);

	LoginResponse signIn(User user);

	
}
