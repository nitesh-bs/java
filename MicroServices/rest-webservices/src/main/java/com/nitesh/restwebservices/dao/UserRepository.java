package com.nitesh.restwebservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitesh.restwebservices.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
