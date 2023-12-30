package com.nitesh.springDemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloWorldForm";
	}
	
	@RequestMapping("/processForm")
	public String processForm() {
		return "processForm";
	}
	
	
	@RequestMapping("/processFormTwo")
	public String readFormData(HttpServletRequest request,Model model) {
		
		model.addAttribute("message","Hi, "+request.getParameter("studName").toUpperCase());
		
		return "processForm";
	}
	
	@RequestMapping("/processFormThree")
	public String readFormData(@RequestParam("studName") String sname,Model model) {
		
		model.addAttribute("message","Hi, "+ sname.toUpperCase());
		
		return "processForm";
	}
}
