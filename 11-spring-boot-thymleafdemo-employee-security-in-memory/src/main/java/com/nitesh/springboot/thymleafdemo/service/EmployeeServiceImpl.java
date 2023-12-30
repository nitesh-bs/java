package com.nitesh.springboot.thymleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nitesh.springboot.thymleafdemo.dao.EmployeeRepository;
import com.nitesh.springboot.thymleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		this.employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
	
//		return employeeRepository.findAll();

		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee employee = null;
		if(result.isPresent()) {
			employee =  result.get();
		}
		else {
			 throw new RuntimeException("Did not find employee id - "+theId);
		}
		return employee;
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchBy(String theName) {
List<Employee> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
		}
		else {
			results = findAll();
		}
		
		return results;
	}

}
