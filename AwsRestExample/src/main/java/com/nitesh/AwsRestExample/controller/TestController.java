package com.nitesh.AwsRestExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/")
	public String sayHello() {
		return "This is from Aws EC2 Instance";
	}
}
