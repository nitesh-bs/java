package com.nitesh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitesh.springdemo.dao.CustomerDAO;
import com.nitesh.springdemo.entity.Customer;
import com.nitesh.springdemo.service.CustomerService;
import com.nitesh.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model model, @RequestParam(required=false) String sort) {

		// get customers from the service
				List<Customer> theCustomers = null;
				
				// check for sort field
				if (sort != null) {
					int theSortField = Integer.parseInt(sort);
					theCustomers = customerService.getCustomers(theSortField);			
				}
				else {
					// no sort field provided ... default to sorting by last name
					theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
				}
				
				// add the customers to the model
				model.addAttribute("customers", theCustomers);

		
//		model.addAttribute("customers", customerService.getCustomers());

		return "list-customers";
	}

	@GetMapping("/customerAdd")
	public String addCustomerForm(Model model) {

		Customer customer = new Customer();

		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

		customerService.saveCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int custId, Model model) {

		Customer customer = customerService.getCustomer(custId);

		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int custId) {

		customerService.deleteCustomer(custId);

		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		return "list-customers";
	}
}
