package com.nitesh.testCase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitesh.testCase.controller.EmployeeNotFound;
import com.nitesh.testCase.entity.Employee;
import com.nitesh.testCase.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		this.employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
	
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee = null;
		if(result.isPresent()) {
			employee =  result.get();
		}
		else {
			 throw new EmployeeNotFound("Did not find employee id - "+theId);
		}
		return employee;
	}

	@Override
	public void save(Employee employee) {
		if(!employeeRepository.selectExistsEmail(employee.getEmail())) {
			
		employeeRepository.save(employee);
		}
		else {
			throw new EmployeeNotFound("Employee email taken");
		}
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
