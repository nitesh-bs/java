package com.nitesh.springboot.thymleafdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nitesh.springboot.thymleafdemo.entity.User;
import com.nitesh.springboot.thymleafdemo.user.CrnUser;

public interface UserService  extends UserDetailsService {

	public User findByUsername(String username);

	public void save(CrnUser user);

	
}
