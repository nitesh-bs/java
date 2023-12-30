package com.rental.stayIn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rental.stayIn.entity.User;
import com.rental.stayIn.repository.MyUserRepository;

@Service
public class UsersServiceImpl implements UserService {
	
	//@Autowired
	public MyUserRepository usersRepository;
	
	public UsersServiceImpl(MyUserRepository myUserRepository) {
		this.usersRepository = myUserRepository;
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return null;
//	}

	@Override
	public List<User> findAllUser() {
		return usersRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> user = usersRepository.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
		
	}

	@Override
	public User registerUser(User user) {
		user.setUserStatus(null);
		User registeredUser= usersRepository.save(user);
		return registeredUser;
	}




}
