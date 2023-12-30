package com.nitesh.springboot.thymleafdemo.dao;

import com.nitesh.springboot.thymleafdemo.entity.User;

public interface UserDao {

	public User findByUserName(String username);
	
	public void save(User user);
}
