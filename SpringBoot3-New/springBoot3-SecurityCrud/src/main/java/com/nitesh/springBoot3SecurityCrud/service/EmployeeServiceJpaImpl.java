package com.nitesh.springBoot3SecurityCrud.service;

import com.nitesh.springBoot3SecurityCrud.entity.Employee;
import com.nitesh.springBoot3SecurityCrud.exception.EmployeeNotFoundException;
import com.nitesh.springBoot3SecurityCrud.repository.EmployeeRepository;
import io.micrometer.observation.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceJpaImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceJpaImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }



    @Override
    public Employee findById(int studentId) {
        Optional<Employee> byId = employeeRepository.findById(studentId);
        if(!byId.isPresent()){
            throw new EmployeeNotFoundException("Student Not Found!!");
        }
        return byId.get();
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int studentId) {
        employeeRepository.deleteById(studentId);
    }
}
