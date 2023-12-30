package com.nitesh.stayIn.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.entity.UserDetails;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.request.UserDetailsRequest;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.view.View;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserDetailsController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Find all owner and user of all status.Only access by admin.")
	@GetMapping("/admin/findAllUserDetails")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<SuccessResponse> findAllUserDetails() throws Exception {
		List<User> users = userService.findAllUserDetails();
		if (users != null) {

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(), "Get All User Details.",
					System.currentTimeMillis(), objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having User Details", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find user details by user id.All can access this api.")
	@GetMapping("/findUserDetailsByUserId/{userId}")
	public ResponseEntity<SuccessResponse> findAllUserDetailsByUserId(@PathVariable Integer userId) {
		UserDetails users = userService.findAllUserDetailsByUserId(userId);
		if (users != null) {
			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
					"Getting User Details of User Id - " + userId + ".", System.currentTimeMillis(), users));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having User Details of User Id - " + userId + ".", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find user details by user details id. All can access this api.")
	@GetMapping("/findUserDetailsByUserDetailId/{userDetailsId}")
	public ResponseEntity<?> findUserDetailsByUserDetailsId(@PathVariable Integer userDetailsId) {
		UserDetails userDetails = userService.findUserDetailsByUserDetailsId(userDetailsId);
		if (userDetails != null) {
			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(),
							"Getting User Details By User Details Id - " + userDetailsId, System.currentTimeMillis(),
							userDetails));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having User Details of User Details Id - " + userDetailsId, System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Update user details by user id. All can access this api.")
	@PutMapping("/updateUserDetails/{userId}")
	public ResponseEntity<?> updateUserDetails(@PathVariable Integer userId,
			@Valid @RequestBody UserDetailsRequest userDetails) {
		UserDetails users = userService.findAllUserDetailsByUserId(userId);
		if (users != null) {

			if (!userDetails.getEmailId().equals(users.getEmailId())) {
				if (userService.findByEmailId(userDetails.getEmailId()) != null) {
					return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Email Address is already taken!", System.currentTimeMillis(), null));
				}
			}

			if (!userDetails.getAadharCardNo().equals(users.getAadharCardNo())) {
				if (userService.findByAadharCardNo(userDetails.getAadharCardNo()) != null) {
					return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Aadhar card is already exists!", System.currentTimeMillis(), null));
				}
			}

			UserDetails updateUserDetails = new UserDetails(userDetails.getFirstName(), userDetails.getLastName(),
					userDetails.getAddress(), userDetails.getCity(), userDetails.getState(), userDetails.getPinCode(),
					userDetails.getMobileNo(), userDetails.getEmailId(), userDetails.getAadharCardNo(),
					users.getUser());

			// userDetails.setUser(users.getUser());
			updateUserDetails.setUserDetailsId(users.getUserDetailsId());
			UserDetails updatedUserDetails = userService.updateUserDetails(updateUserDetails);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(),
							"Successfully updated user details of user id - " + userId, System.currentTimeMillis(),
							updatedUserDetails));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having User Details of User Id - " + userId, System.currentTimeMillis(), null));
		}
	}

}
