package com.nitesh.springdemo.dao;

import java.util.List;

import com.nitesh.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers(int theSortField);

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int custId);

	public void deleteCustomer(int custId);

	public List<Customer> searchCustomers(String theSearchName);
}
