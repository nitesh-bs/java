package com.nitesh.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
