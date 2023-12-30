package com.nitesh.product.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nitesh.product.service.FileStorageService;
import com.nitesh.product.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private  FileStorageService fileStorageService;


	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("product",productService.findAllProducts());
		return "index";
	}
	
	
	
}
