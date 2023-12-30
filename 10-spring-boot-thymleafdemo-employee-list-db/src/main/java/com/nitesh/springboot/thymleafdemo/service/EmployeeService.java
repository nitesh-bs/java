package com.nitesh.springboot.thymleafdemo.service;

import java.util.List;

import com.nitesh.springboot.thymleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee employee);
	
	public void deleteById(int theId);
}
