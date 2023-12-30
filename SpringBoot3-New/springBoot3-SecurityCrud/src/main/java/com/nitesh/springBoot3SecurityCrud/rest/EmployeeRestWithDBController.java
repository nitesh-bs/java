package com.nitesh.springBoot3SecurityCrud.rest;

import com.nitesh.springBoot3SecurityCrud.entity.Employee;
import com.nitesh.springBoot3SecurityCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jpa")
public class EmployeeRestWithDBController {

    private EmployeeService  employeeService;

    @Autowired
    public EmployeeRestWithDBController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employee){
        employee.setStudId(0);

        return employeeService.saveOrUpdate(employee);
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employee){
        return employeeService.saveOrUpdate(employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteById(@PathVariable int employeeId){
        employeeService.deleteById(employeeId);
    }


}
