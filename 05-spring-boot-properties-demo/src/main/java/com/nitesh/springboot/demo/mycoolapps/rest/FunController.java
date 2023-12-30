package com.nitesh.springboot.demo.mycoolapps.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunController {

	@Value("${coach.name}")
	private String coachName;
	
	@Value("${team.name}")
	private String teamName;
	
	
	@GetMapping("/")
	public String sayHello() {
		return "Hello World! Time on server is "+LocalDateTime.now();
				
	}
	
	
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "do daily workout :"+coachName+ " === "+teamName;
	}
}
