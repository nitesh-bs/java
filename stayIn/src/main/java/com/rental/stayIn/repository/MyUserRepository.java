package com.rental.stayIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rental.stayIn.entity.User;

@Repository
public interface MyUserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

}
