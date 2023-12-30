package com.nitesh.springDemo.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

	/*
	 * properties file parsing
	 * @Value("#{countryOptionsXml}") public Map<String, String> countryOptionsXml;
	 */

	@RequestMapping("showForm")
	public String studentForm(Model model) {

		model.addAttribute("student", new Student());
		
//		model.addAttribute("theCountryOptions", countryOptionsXml);
		
		return "studentForm";
	}

	@RequestMapping("processForm")
	public String processForm(@ModelAttribute("student") Student student) {

		System.out.println("Student :" + student.getFirstName());

		return "studentConfirmation";
	}
}
