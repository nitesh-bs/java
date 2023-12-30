package com.nitesh.restwebservices.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.nitesh.restwebservices.bean.User;


@Component
public class UserDao {

	private static List<User> users = new ArrayList<>();
	private static int userIdCount = 0;
	
	static {
		users.add(new User(++userIdCount, "nitesh", LocalDate.now().minusYears(30)));
		users.add(new User(++userIdCount, "raj", LocalDate.now().minusYears(10)));
		users.add(new User(++userIdCount, "pratik", LocalDate.now().minusYears(23)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User addUser(User user) {
		user.setId(++userIdCount);
		users.add(user);
		
		return user;
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
		
	}
}
