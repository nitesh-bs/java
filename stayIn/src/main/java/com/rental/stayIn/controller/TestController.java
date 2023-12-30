package com.rental.stayIn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/login")
	public String sayHello() {
		return "Hello World";
	}
}
