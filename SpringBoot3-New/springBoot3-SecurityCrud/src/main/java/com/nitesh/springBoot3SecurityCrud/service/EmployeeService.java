package com.nitesh.springBoot3SecurityCrud.service;

import com.nitesh.springBoot3SecurityCrud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int studentId);

    Employee saveOrUpdate(Employee employee);

    void deleteById(int studentId);
}
