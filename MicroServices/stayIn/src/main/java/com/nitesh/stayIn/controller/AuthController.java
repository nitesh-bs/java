package com.nitesh.stayIn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.entity.User;
import com.nitesh.stayIn.entity.UserDetails;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.request.SignInRequest;
import com.nitesh.stayIn.request.SignUpRequest;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.utils.JwtUtils;
import com.nitesh.stayIn.view.View;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

//	@GetMapping("/")
//	public ResponseEntity<?> logintest() {
//		return ResponseEntity.ok("Login successfull");
//	}

//	@PostMapping("/loginUser")
//	public ResponseEntity<?> login(@RequestBody User user) throws Exception {
//
//		User createJwtToken = userService.createJwtToken(user);
//
//		if(createJwtToken != null) {
//		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(createJwtToken.getUserToken());
//
////		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), "Logging successfull!",
////				System.currentTimeMillis(), createJwtToken));
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//				.header(HttpHeaders.AUTHORIZATION, createJwtToken.getUserToken()).body(new SuccessResponse(
//						HttpStatus.OK.value(), "Logging successfull!", System.currentTimeMillis(), userService.findAllUserDetailsByUserId(createJwtToken.getUserId())));
//		}
//		else {
//
//			return ResponseEntity.status(401).body(new SuccessResponse(HttpStatus.UNAUTHORIZED.value(),"User Not Active!",System.currentTimeMillis(),null));
//		}
//	}

	@Operation(summary = "Login for all users.")
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody SignInRequest signInRequest) throws Exception {

		User createJwtToken = userService
				.createJwtToken(new User(signInRequest.getUsername(), signInRequest.getPassword()));

		if (createJwtToken != null) {
			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(createJwtToken.getUserToken());

//		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), "Logging successfull!",
//				System.currentTimeMillis(), createJwtToken));

			final ObjectMapper objectMapper = new ObjectMapper();
			String response = objectMapper.writerWithView(View.UserDetailsWithUserView.class)
					.writeValueAsString(createJwtToken);

			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.header(HttpHeaders.AUTHORIZATION, createJwtToken.getUserToken())
					.body(new SuccessResponse(HttpStatus.OK.value(), "Logging successfull!", System.currentTimeMillis(),
							objectMapper.getFactory().createParser(response).readValueAsTree()));
		} else {

			return ResponseEntity.status(401).body(new SuccessResponse(HttpStatus.UNAUTHORIZED.value(),
					"User Not Active!", System.currentTimeMillis(), null));
		}
	}

	@Operation(summary = "Logout for all user.")
	@PostMapping("/logout")
	public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					null, null, null);
			SecurityContextHolder.getContext().setAuthentication(null);

			ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new SuccessResponse(
					HttpStatus.OK.value(), "Logout successfull!", System.currentTimeMillis(), null));
//			 new SecurityContextLogoutHandler().logout(request, response,usernamePasswordAuthenticationToken);

		}
		return ResponseEntity.ok(
				new SuccessResponse(HttpStatus.OK.value(), "Logout successfull!", System.currentTimeMillis(), null));
	}

//	@PostMapping("/register")
//	public ResponseEntity<?> register(@RequestBody com.nitesh.stayIn.entity.User user) {
//
//		System.out.println(user);
//		if (userService.findByUsername(user.getUsername()) != null) {
//			return ResponseEntity.badRequest()
//					.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Username is already taken!",
//							System.currentTimeMillis(), userService.findByUsername(user.getUsername())));
//		}
//
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//		if (user.getUserType().equals("ROLE_A")) {
//			user.setUserStatus("A");
//		} else if (user.getUserType().equals("ROLE_O")) {
//			user.setUserStatus("V");
//		} else {
//			user.setUserStatus("V");
//		}
//
//		userService.saveUser(user);
//
//		return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(), "register successfully",
//				System.currentTimeMillis(), user));
//	}

//	@PostMapping("/registerUser")
//	public ResponseEntity<?> registerUser(@RequestBody UserDetails userDetails) {
//		System.out.println("User Details : " + userDetails);
//		User users = userDetails.getUser();
//
//		System.out.println("User : " + users);
//
//		if (userService.findByUsername(userDetails.getUser().getUsername()) != null) {
//			return ResponseEntity.badRequest()
//					.body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(), "Error: Username is already taken!",
//							System.currentTimeMillis(),
//							userService.findByUsername(userDetails.getUser().getUsername())));
//		}
//
//		userDetails.getUser().setPassword(passwordEncoder.encode(userDetails.getUser().getPassword()));
//
//		if (userDetails.getUser().getUserType().equals("ROLE_A")) {
//			userDetails.getUser().setUserStatus("A");
//		} else if (userDetails.getUser().getUserType().equals("ROLE_O")) {
//			userDetails.getUser().setUserStatus("V");
//		} else {
//			userDetails.getUser().setUserStatus("V");
//		}
//
//		UserDetails registerdUserDetails = userService.registerUserD(userDetails);
//		return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(), "register User",
//				System.currentTimeMillis(), registerdUserDetails));
//	}

	@Operation(summary = "Registration api.")
	@PostMapping("/signUp")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		System.out.println("User Details : " + signUpRequest);
		// User users = userDetails.getUser();

		System.out.println("User : " + signUpRequest);

		if (userService.findByUsername(signUpRequest.getUsername()) != null) {
			return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Username is already taken!", System.currentTimeMillis(), null));
		}

		if (userService.findByEmailId(signUpRequest.getEmailId()) != null) {
			return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Email Address is already taken!", System.currentTimeMillis(), null));
		}

		if (userService.findByAadharCardNo(signUpRequest.getAadharCardNo()) != null) {
			return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
					"Aadhar card is already exists!", System.currentTimeMillis(), null));
		}

		signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		if (signUpRequest.getUserType().equals("ROLE_A")) {
			signUpRequest.setUserStatus("A");
		} else if (signUpRequest.getUserType().equals("ROLE_O")) {
			signUpRequest.setUserStatus("V");
		} else {
			signUpRequest.setUserStatus("A");
		}

		UserDetails registerdUserDetails = userService.registerUserD(signUpRequest);
		return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED.value(),
				"You have Successfully Registered", System.currentTimeMillis(), registerdUserDetails));
	}

	@Operation(summary = "Change username or password.")
	@PostMapping("/changeUsernameOrPassword")
	public ResponseEntity<?> changeUsernameOrPassword(@RequestParam("userId") Integer userId,
			@RequestParam("username") String username,
			@RequestParam(name = "password", required = false) String password) {
		User user = userService.findByUserId(userId);
		System.out.println("df" + username + "fgf" + password);
		if (user != null) {
			if (user.getUserStatus().equals("A")) {

				if (user.getUsername().equals(username) && password == null) {
					return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
							"Need to change username or password!", System.currentTimeMillis(), null));

				} else if (!user.getUsername().equals(username) && password == null) {
					if (userService.findByUsername(username) != null) {
						return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
								"Username is already taken!", System.currentTimeMillis(), null));

					} else {
						user.setUsername(username);
						userService.saveUser(user);
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								null, null, null);
						SecurityContextHolder.getContext().setAuthentication(null);

						ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
						return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
								.body(new SuccessResponse(HttpStatus.OK.value(),
										"Credential change successfully! Need to re-login.", System.currentTimeMillis(),
										null));
					}
				} else if (!user.getUsername().equals(username) && password != null) {
					if (userService.findByUsername(username) != null) {
						return ResponseEntity.badRequest().body(new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
								"Username is already taken!", System.currentTimeMillis(), null));

					} else {
						user.setUsername(username);
						user.setPassword(passwordEncoder.encode(password));
						userService.saveUser(user);
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								null, null, null);
						SecurityContextHolder.getContext().setAuthentication(null);

						ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
						return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
								.body(new SuccessResponse(HttpStatus.OK.value(),
										"Credential change successfully! Need to re-login.", System.currentTimeMillis(),
										null));
					}
				} else if (user.getUsername().equals(username) && password != null) {
					user.setPassword(passwordEncoder.encode(password));
					userService.saveUser(user);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							null, null, null);
					SecurityContextHolder.getContext().setAuthentication(null);

					ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
							.body(new SuccessResponse(HttpStatus.OK.value(),
									"Credential change successfully! Need to re-login.", System.currentTimeMillis(),
									null));

				}
			}
		}
		return null;
	}
}
