package com.nitesh.springboot.thymleafdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nitesh.springboot.thymleafdemo.entity.User;
import com.nitesh.springboot.thymleafdemo.service.UserService;
import com.nitesh.springboot.thymleafdemo.user.CrnUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showFormRegistration")
	public String showRegistrationForm(Model model) {		
		model.addAttribute("crmuser",new CrnUser());
		return "registration-form";
	}
	
	@PostMapping("processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmuser") CrnUser crnUser,BindingResult bindingResult,Model model) {
		
		String username = crnUser.getUserName();
		
		if(bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User user = userService.findByUsername(username);
		if(user != null) {
			model.addAttribute("crmuser",new CrnUser());
			model.addAttribute("registrationError","User Already exists!!!");
			return "registration-form";
		}
		
		userService.save(crnUser);
		
		return "registration-confirmation";
	}
}
