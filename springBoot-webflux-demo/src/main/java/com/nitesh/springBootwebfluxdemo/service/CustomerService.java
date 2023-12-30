package com.nitesh.springBootwebfluxdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.springBootwebfluxdemo.dao.CustomerDao;
import com.nitesh.springBootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;
	
	public List<Customer> loadAllCustomers(){
		long start = System.currentTimeMillis();
		List<Customer> customers = dao.getAllCustomers();
		long end = System.currentTimeMillis();
		
		System.out.println("Execution Time : "+(end - start));
		return customers;
	}
	
	public Flux<Customer> loadAllCustomersFlux(){
		long start = System.currentTimeMillis();
		Flux<Customer> customers = dao.getAllCustomersFlux();
		long end = System.currentTimeMillis();
		
		System.out.println("Execution Time : "+(end - start));
		return customers;
	}
}
