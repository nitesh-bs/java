package com.nitesh.springBootJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springBootJwt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
