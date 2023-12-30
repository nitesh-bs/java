package com.nitesh.DemoValidationResuability.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.DemoValidationResuability.entity.Student;

@RestController
@RequestMapping("/api")
public class DemoController {

	@GetMapping("/")
	public String sayHello(@jakarta.validation.Valid @RequestBody Student student,BindingResult bindingResult)
	{	
		System.out.println("students: : : "+student);
		if(bindingResult.hasErrors()) {			
			return "Error "+bindingResult.getFieldError().getDefaultMessage();
		}
		return "Hey Good morning!";
	}
}
