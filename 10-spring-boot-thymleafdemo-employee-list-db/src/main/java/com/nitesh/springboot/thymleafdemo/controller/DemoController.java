package com.nitesh.springboot.thymleafdemo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/hello")
	public String sayhello(Model model) {
		model.addAttribute("theDate",new Date());
		
		return "helloworld";
	}
	
	
}
