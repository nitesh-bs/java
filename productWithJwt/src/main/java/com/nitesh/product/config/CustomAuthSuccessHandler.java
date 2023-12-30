package com.nitesh.product.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nitesh.product.entity.JwtRequest;
import com.nitesh.product.entity.JwtResponse;
import com.nitesh.product.entity.Role;
import com.nitesh.product.entity.User;
import com.nitesh.product.service.JwtService;
import com.nitesh.product.service.UserService;
import com.nitesh.product.util.Jwt;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private Jwt jwtUtil;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();

		System.out.println("userName=" + userName + "principal=" + authentication.getPrincipal() + "authority="
				+ authentication.getAuthorities());

		User user = userService.findUserByUserName(userName);

		System.out.println();
		System.out.println(user);
		
		 JwtRequest jwtRequest = new JwtRequest();
		 jwtRequest.setUserName(user.getUserName());
		 jwtRequest.setUserPassword(user.getPassword());
		 
		 JwtResponse jwtResponse  = null;
		try {
			
			final UserDetails userDetails = userService.loadUserByUsername(userName);
			
			String newGeneratedToken = jwtUtil.generateToken(userDetails);
			jwtResponse = new JwtResponse(user, newGeneratedToken);

			System.out.println();
			System.out.println(jwtResponse);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	
		
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		for (Role iterable_element : user.getRole()) {
			if (iterable_element.getName().contains("ADMIN")) {

//request.setAttribute("Authorization", "Bearer "+jwtResponse.getJwtToken());
RequestDispatcher reqd = request.getRequestDispatcher(request.getContextPath() + "/admin/dashboard");
//reqd.forward(request, response);
	response.addHeader("Authorization", "Bearer "+jwtResponse.getJwtToken());
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
				break;
			} 

		}

		for (Role iterable_element : user.getRole()) {
			if (iterable_element.getName().contains("USER")) {
//				request.setAttribute("Authorization", "Bearer "+jwtResponse.getJwtToken());
				RequestDispatcher reqd = request.getRequestDispatcher(request.getContextPath() + "/");
				reqd.forward(request, response);
//				response.sendRedirect(request.getContextPath() + "/");
				break;
			} 

		}
		// now place in the session

		/*
		 * HttpSession session = request.getSession(); session.setAttribute("user",
		 * user.getUserName());
		 * 
		 * // forward to home page
		 * 
		 * response.sendRedirect(request.getContextPath() + "/");
		 */
	}
	

}
