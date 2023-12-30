package com.nitesh.product.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nitesh.product.entity.User;

public interface UserDao  {
	
	public User findUserByUserName(String userName);
	
	public void saveUser(User user);
}
