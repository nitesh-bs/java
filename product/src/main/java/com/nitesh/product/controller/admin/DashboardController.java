package com.nitesh.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nitesh.product.service.ProductService;

@Controller
@RequestMapping("admin")
public class DashboardController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {
		
		model.addAttribute("totalProduct",productService.totalProduct());
		return "admin/dashboard";
	}

	
}
