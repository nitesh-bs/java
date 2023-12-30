package com.nitesh.springboot.thymleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nitesh.springboot.thymleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findAllByOrderByLastNameAsc();

	public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String theName, String theName2);
}
