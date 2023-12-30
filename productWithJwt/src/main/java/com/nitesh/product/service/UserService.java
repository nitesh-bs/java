package com.nitesh.product.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nitesh.product.entity.User;
import com.nitesh.product.model.CrnUser;

public interface UserService extends UserDetailsService{

	
	public User findUserByUserName(String userName);
	
	public void saveUser(CrnUser user);
}
