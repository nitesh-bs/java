package com.nitesh.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.cruddemo.dao.EmployeeDAO;
import com.nitesh.springboot.cruddemo.entity.Employee;
import com.nitesh.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);

		if (employee == null) {
			throw new EmployeeNotFound("Employee id not found - " + employeeId);
		}
		return employee;
	}
	

	@PostMapping("/employees")
	public Employee addEmployees(@RequestBody Employee employee) {

		employee.setId(0);

		employeeService.save(employee);

		return employee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {

		/*
		 * Employee theEmployee = employeeService.findById(employee.getId());
		 * 
		 * if(theEmployee == null) { throw new
		 * RuntimeException("Employee id not found - "+theEmployee.getId()); }
		 */
		employeeService.save(employee);

		return employee;

	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);

		if (employee == null) {
			throw new EmployeeNotFound("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted Employee Id : " + employeeId;

	}
}
