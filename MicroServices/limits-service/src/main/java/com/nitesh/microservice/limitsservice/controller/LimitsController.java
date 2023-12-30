package com.nitesh.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.microservice.limitsservice.bean.Limits;
import com.nitesh.microservice.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public Limits getAllLimits() {
		return new Limits(configuration.getMinimum(), configuration.getMaximun());
	}
	
}
