package com.nitesh.SpringBootPDF;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nitesh.SpringBootPDF.model.Employee;
import com.nitesh.SpringBootPDF.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBootPdfApplication implements CommandLineRunner{

    @Autowired
    EmployeeRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPdfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (repository.count() == 0) {
            // save a list of Employees
            repository.saveAll(Arrays.asList(
                        new Employee("Adam", "John"), 
                           new Employee("Sibin", "M"),
                             new Employee("Arun", "Mohan"), 
                                new Employee("Scott", "Morrison"),
                                   new Employee("Hikaru", "Nakamura"), 
                                     new Employee("Ishivaka", "Yusuke")));
        }
	}

}
