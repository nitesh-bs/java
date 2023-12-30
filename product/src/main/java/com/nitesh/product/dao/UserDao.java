package com.nitesh.product.dao;


import com.nitesh.product.entity.User;

public interface UserDao  {
	
	public User findUserByUserName(String userName);
	
	public void saveUser(User user);
}
