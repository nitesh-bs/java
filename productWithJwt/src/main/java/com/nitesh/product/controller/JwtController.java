package com.nitesh.product.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.product.entity.JwtRequest;
import com.nitesh.product.entity.JwtResponse;
import com.nitesh.product.service.JwtService;


@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping({"/authenticate"})
	private JwtResponse createJwtToken(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws Exception {
		 JwtRequest jwtRequest = new JwtRequest();
		 jwtRequest.setUserName(username);
		 jwtRequest.setUserPassword(password);
//		 JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);
//		 System.out.println( "Bearer "+jwtResponse.getJwtToken());
////		 httpServletRequest.setAttribute("Authorization","Bearer "+jwtResponse.getJwtToken());
//		 httpServletResponse.addHeader("Authorization", "Bearer "+jwtResponse.getJwtToken());
////		 httpServletResponse.sendRedirect("/admin/dashboard");
//		 RequestDispatcher reqd = httpServletRequest.getRequestDispatcher(httpServletRequest.getContextPath() + "/admin/dashboard");
//		reqd.forward(httpServletRequest, httpServletResponse);
		
		return jwtService.createJwtToken(jwtRequest);
	}
}
