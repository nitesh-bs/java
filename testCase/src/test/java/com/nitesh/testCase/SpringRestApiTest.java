package com.nitesh.testCase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.nitesh.testCase.entity.Employee;
import com.nitesh.testCase.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringRestApiTest {

	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost:";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void setUp() {
		baseUrl=baseUrl.concat(port+"/api/employees");
	}
	
	@Test
	public void testAddEmployee() {
		Employee employee = new Employee(0,"abc", "avc", "abcded@gmail.com");
		Employee response = restTemplate.postForObject(baseUrl, employee, Employee.class);
		assertEquals("abc", response.getFirstName());
	}
	
	@Test
	public void testAllEmployee() {
		List<Employee> employees = restTemplate.getForObject(baseUrl, List.class);
		assertEquals(9,	 employeeRepository.findAll().size());
		
	}

	@Test
	public void testFindByIdEmployee() {
		Employee emp = restTemplate.getForObject(baseUrl+"/{id}", Employee.class,1);
		assertAll(()->assertNotNull(emp),
				()->assertEquals(1, emp.getId()));
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee(9,"abcde", "avc", "abcdede@gmail.com");
		restTemplate.put(baseUrl, employee);
		assertAll(()->assertNotNull(employeeRepository.findById(9).get()),
				()->assertEquals("abcd", employeeRepository.findById(9).get().getFirstName()));
	}
	
	
	@Test
	public void deleteEmployee(){
		restTemplate.delete(baseUrl+"/{employeeId}",9);
		
		assertEquals(8, employeeRepository.findAll().size());
	}
}
