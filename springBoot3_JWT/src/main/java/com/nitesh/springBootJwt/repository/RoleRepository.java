package com.nitesh.springBootJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springBootJwt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
