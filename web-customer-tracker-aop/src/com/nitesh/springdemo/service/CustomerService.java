package com.nitesh.springdemo.service;

import java.util.List;

import com.nitesh.springdemo.entity.Customer;

public interface CustomerService {

//	public List<Customer> getCustomers();
	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int custId);

	public void deleteCustomer(int custId);

	public List<Customer> searchCustomers(String theSearchName);
}
