package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/showLoginPage")
	private String showLoginPage() {
		return "bootstrap-login";
	}
}
