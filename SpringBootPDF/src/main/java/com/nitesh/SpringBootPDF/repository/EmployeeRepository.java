package com.nitesh.SpringBootPDF.repository;

import org.springframework.data.repository.CrudRepository;

import com.nitesh.SpringBootPDF.model.Employee;

public interface EmployeeRepository 
             extends CrudRepository<Employee, Long> {
}