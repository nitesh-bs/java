package com.nitesh.mongoDb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.mongoDb.User;
import com.nitesh.mongoDb.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public void saveUser(User user) {
		userRepository.findUserByEmail(user.getEmail())
		.ifPresentOrElse(u -> {
			System.out.println("Already exists user email : "+u.getEmail());
		}, ()->{
			userRepository.save(user) ;
			System.out.println("Inserted user : "+user); 
		});
	}
}
