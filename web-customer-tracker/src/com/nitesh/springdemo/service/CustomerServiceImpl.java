package com.nitesh.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.springdemo.dao.CustomerDAO;
import com.nitesh.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customers) {
		customerDAO.saveCustomer(customers);
	}

	@Override
	@Transactional
	public Customer getCustomer(int custId) {
		return customerDAO.getCustomer(custId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int custId) {
		customerDAO.deleteCustomer(custId);
	}
	
	@Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }

}
