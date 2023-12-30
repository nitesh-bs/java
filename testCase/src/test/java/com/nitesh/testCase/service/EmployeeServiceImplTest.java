package com.nitesh.testCase.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nitesh.testCase.controller.EmployeeNotFound;
import com.nitesh.testCase.entity.Employee;
import com.nitesh.testCase.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	private EmployeeServiceImpl employeeServiceImpl;
	
	
//	@ExtendWith(MockitoExtension.class) if you don't want to use this annotation.
//	AutoCloseable autoCloseable ;
	
	@BeforeEach
	void setup() {
		
//		AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
	employeeServiceImpl = new EmployeeServiceImpl(employeeRepository);
	}
	
	
//	@AfterEach
//	void tearDown() {
//		try {
//			this.autoCloseable.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void testEmployeeServiceImpl() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		employeeServiceImpl.findAll();
		
		verify(employeeRepository).findAll();
	} 

	@Test 
	void testFindById() {
//		fail("Not yet implemented");
		Employee employee = new Employee(1,"abc", "avc", "abcde@gmail.com");
		given(employeeServiceImpl.findById(employee.getId()))
//		.willThrow(new RuntimeErrorException("EmployeeNotFound"))
		.willReturn((employee));
		assertEquals(employee, employeeServiceImpl.findById(employee.getId()));
		verify(employeeRepository.findById(1).get());
		
		

//Mockito.when(exampleRepository.findById(id)).thenReturn(Optional.of(example));
//
//Example result = exampleService.findById(id);
//
//// Assert that the result is the same as the example
//assertEquals(example, result);
	}
	
	@Test
	void testSave() {
		Employee employee = new Employee(0,"abc", "avc", "abcde@gmail.com");

		//employeeServiceImpl.save(employee);
		
//		ArgumentCaptor<Employee> argumentCaptor = 
//				ArgumentCaptor.forClass(Employee.class);
//		
//		verify(employeeRepository).save(argumentCaptor.capture());
//		
//		Employee capturedEmployee = argumentCaptor.getValue();
//		
//		assertThat(capturedEmployee).isEqualTo(employee);
		
		given(employeeRepository.selectExistsEmail(employee.getEmail()))
		.willReturn(true);
		
		assertThatThrownBy(()->employeeServiceImpl.save(employee))
		.isInstanceOf(EmployeeNotFound.class)
		.hasMessageContaining("Employee email taken");
		
		verify(employeeRepository,never()).save(any());
				
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

}
