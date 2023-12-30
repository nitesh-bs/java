package com.nitesh.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.UserService.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	

}
