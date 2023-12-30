package com.nitesh.springBootwebfluxdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springBootwebfluxdemo.dto.Customer;
import com.nitesh.springBootwebfluxdemo.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public List<Customer> getAllCustomers(){
		return customerService.loadAllCustomers();
	}
	
	@GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllCustomersReactiveStream(){
		return customerService.loadAllCustomersFlux();
	}
}
