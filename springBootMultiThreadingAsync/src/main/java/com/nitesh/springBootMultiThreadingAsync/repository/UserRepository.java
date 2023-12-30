package com.nitesh.springBootMultiThreadingAsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springBootMultiThreadingAsync.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
 
}
