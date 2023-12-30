package com.nitesh.stayIn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.view.View;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserMasterController {

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Operation(summary = "Find all rent owners by status.by default status is A.Only access by admin.")
	@GetMapping("/admin/findAllRentOwnerByStatus")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<SuccessResponse> findAllActiveRentOwner(
			@RequestParam(value = "status", required = false, defaultValue = "A") String status) throws Exception {
		List<User> users = userService.findAllActiveRentOwner(status);
		if (users != null) {

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200)
					.body(new SuccessResponse(HttpStatus.OK.value(), "get All Rent Owner By Status",
							System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having Rent Owner", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find All Rent Owners of all status. Only access by admin.")
	@GetMapping("/admin/findAllRentOwners")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<SuccessResponse> findAllRentOwners() throws Exception {
		List<User> users = userService.findRentOwners();
		if (users != null) {

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(), "get All Rent Owner",
					System.currentTimeMillis(), objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having Rent Owner", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Find All User of all status.only access by admin.")
	@GetMapping("/admin/findAllUsers")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<SuccessResponse> findAllAppUsers() throws Exception {
		List<User> users = userService.findAppUsers();
		if (users != null) {

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithUserView.class).writeValueAsString(users);

			return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(), "get All Users",
					System.currentTimeMillis(), objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(), "Not having Users",
					System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Update user status by user id. user status are A/V/D.Only access by admin.")
	@PutMapping("/admin/updateUserStatus/{userId}/{userStatus}")
	@PreAuthorize("hasRole('ROLE_A')")
	public ResponseEntity<?> updateUserStatus(@PathVariable(name = "userId", required = true) Integer userId,
			@PathVariable(name = "userStatus", required = true) String userStatus) throws Exception {
		User users = userService.findByUserId(userId);
		if (users != null) {
			if (userStatus.equals("A") || userStatus.equals("V") || userStatus.equals("D")) {
				users.setUserStatus(userStatus);
				User updateUser = userService.saveUser(users);

				final ObjectMapper objectMapper = new ObjectMapper();
				String response = objectMapper.writerWithView(View.UserView.class).writeValueAsString(updateUser);

				return ResponseEntity.status(200).body(new SuccessResponse(HttpStatus.OK.value(),
						"Successfully updated user status of User Id - " + userId + ".", System.currentTimeMillis(),
						objectMapper.getFactory().createParser(response).readValueAsTree()));
			} else {
				return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
						"Invalid User Status!", System.currentTimeMillis(), null));
			}
		} else {
			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),
					"Not having User of User Id - " + userId, System.currentTimeMillis(), null));
		}

	}

//	@GetMapping("/updateUserPassword")
//	public ResponseEntity<?> changePassword(@RequestBody User user){
//		User users = userService.findByUserId(user.getUserId());
//		if(users != null) {
//
//			//update userPassword
//
//			return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(), "register User",
//					System.currentTimeMillis(), users));
//		}else {
//			return ResponseEntity.status(404).body(new SuccessResponse(HttpStatus.NOT_FOUND.value(),"Not having User of User Id - "+user.getUserId(),System.currentTimeMillis(),null));
//
//		}
//	}

}
