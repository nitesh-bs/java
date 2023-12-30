package com.nitesh.HotelService.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@GetMapping
	public ResponseEntity<?> getStaffs(){
		
		List<String> lt = Arrays.asList("dsas","dffd","fdfgf");
		return ResponseEntity.status(200).body(lt);
	}
}
