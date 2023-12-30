package com.nitesh.springBootBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springBootBatch.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
