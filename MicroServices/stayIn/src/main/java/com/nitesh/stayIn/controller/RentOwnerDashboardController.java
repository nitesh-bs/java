package com.nitesh.stayIn.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.service.RentOwnerDashboardService;
import com.nitesh.stayIn.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RentOwnerDashboardController {

	@Autowired
	private RentOwnerDashboardService rentOwnerDashboardService;

	@Autowired
	private UserService userService;

	@Operation(summary = "Rent Owner Dashboard.Only access by rent owner & admin")
	@GetMapping("/myDashboard")
	@PreAuthorize("hasAnyRole('ROLE_O','ROLE_A')")
	public ResponseEntity<?> rentOwnerDashboard(@RequestParam("userId") Integer userId) {

		User user = userService.findRentOwnerByUserId(userId);
		if (user != null) {
			HashMap<String, Integer> hm = new HashMap<>();
			hm.put("totalRentHouses", rentOwnerDashboardService.totalRentHousesByRentOwnerId(userId));
			hm.put("totalPendingVerificationOfRentHouses",
					rentOwnerDashboardService.totalPendingVerificationOfRentHousesByRentOwnerId(userId));
			hm.put("totalDisapprovedVerificationOfRentHouses",
					rentOwnerDashboardService.totalDisapprovedVerificationOfRentHousesByRentOwnerId(userId));
			hm.put("totalActiveRentHouses", rentOwnerDashboardService.totalActiveRentHousesByRentOwnerId(userId));

			hm.put("totalComplatedBooking", rentOwnerDashboardService.totalComplatedBookingByRentOwnerId(userId));

			hm.put("totalComplatedBookingAmount",
					rentOwnerDashboardService.totalComplatedBookingAmountByRentOwnerId(userId));

			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"RentOwner Dashboard Details.", System.currentTimeMillis(), hm));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(), "User not found!",
					System.currentTimeMillis(), null));
		}
	}
}
