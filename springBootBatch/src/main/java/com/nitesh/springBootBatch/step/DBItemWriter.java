package com.nitesh.springBootBatch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nitesh.springBootBatch.entity.Employee;
import com.nitesh.springBootBatch.repository.EmployeeRepository;

@Component
public class DBItemWriter implements ItemWriter<Employee>{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void write(List<? extends Employee> items) throws Exception {
		System.out.println("Emp :: "+items);
		employeeRepository.saveAll(items);
		
	}

}
