package com.nitesh.testCase.employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.nitesh.testCase.entity.Employee;
import com.nitesh.testCase.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeRepositoryTest {


	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Test
	void isEmailExists() {
		
//		Employee employee = new Employee(0,"abc", "avc", "abcde@gmail.com");
//
//		employeeRepository.save(employee);
		
		Boolean exists = employeeRepository.selectExistsEmail("juan@luv2code.com");
		
		assertThat(exists).isTrue(); 
	}
}
