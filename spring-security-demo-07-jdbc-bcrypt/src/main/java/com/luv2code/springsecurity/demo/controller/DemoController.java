package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/employees")
	public String homePage() {
		return "home";
	}

	@GetMapping("leaders")
	public String showLeaders() {
		return "leaders";
	}

	@GetMapping("system")
	public String showSystem() {
		return "system";
	}

	@GetMapping("/")
	public String showLanding() {

		return "landing";
	}

}
