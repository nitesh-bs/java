package com.nitesh.springboot.demo.mycoolapps.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunController {


	@GetMapping("/")
	public String sayHello() {
		return "Hello World! Time on server is "+LocalDateTime.now();
				
	}
	
}
