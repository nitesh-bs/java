package com.nitesh.springboot.thymleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitesh.springboot.thymleafdemo.entity.Employee;
import com.nitesh.springboot.thymleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		List<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees",employees);
		return "employees/list-employee";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String formEmployee(Model model) {
		
		Employee employee = new  Employee();
		
		model.addAttribute("employee",employee);
		
		return "employees/employee-from";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	
		employeeService.save(employee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model model) {
		
		Employee  employee = employeeService.findById(theId);
		
		if (employee == null) {
			throw new EmployeeNotFound("Employee id not found - " + theId);
		}
		model.addAttribute("employee",employee);
		
		return "employees/employee-from";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId,Model model) {
		Employee  employee = employeeService.findById(theId);
		
		if (employee == null) {
			throw new EmployeeNotFound("Employee id not found - " + theId);
		}
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/search")
	public String delete(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Employee> theEmployees = employeeService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		// send to /employees/list
		return "/employees/list-employee";
		
	}
		
}
