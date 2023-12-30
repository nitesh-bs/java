package com.nitesh.stayIn.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.service.AdminDashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AdminDashboardController {

	@Autowired
	private AdminDashboardService adminDashboardService;

	@Operation(summary = "Admin Dashboard.Only access by admin.")
	@GetMapping("/admin/dashboard")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> findDashboardData() {

		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("totalRentOwner", adminDashboardService.totalRentOwner());
		hm.put("totalPendingVerificationOfRentOwner", adminDashboardService.totalPendingVerificationOfRentOwner());
		hm.put("totalDisapprovedVerificationOfRentOwner",
				adminDashboardService.totalDisapprovedVerificationOfRentOwner());
		hm.put("totalUser", adminDashboardService.totalUser());
//		hm.put("totalPendingVerificationOfRentUser", adminDashboardService.totalPendingVerificationOfRentUser());
		hm.put("totalRentHouses", adminDashboardService.totalRentHouses());
		hm.put("totalPendingVerificationOfRentHouses", adminDashboardService.totalPendingVerificationOfRentHouses());
		hm.put("totalDisapprovedVerificationOfRentHouses",
				adminDashboardService.totalDisapprovedVerificationOfRentHouses());
		hm.put("totalActiveRentHouses", adminDashboardService.totalActiveRentHouses());
		hm.put("totalActiveRentOwner", adminDashboardService.totalActiveRentOwner());

		return ResponseEntity.status(200).body(
				new SuccessResponse(HttpStatus.OK.value(), "Admin Dashboard Details.", System.currentTimeMillis(), hm));

	}
}
