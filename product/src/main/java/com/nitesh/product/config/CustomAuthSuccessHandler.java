package com.nitesh.product.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nitesh.product.entity.Role;
import com.nitesh.product.entity.User;
import com.nitesh.product.service.UserService;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

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

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		for (Role iterable_element : user.getRole()) {
			if (iterable_element.getName().contains("ADMIN")) {
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
				break;
			} 

		}

		for (Role iterable_element : user.getRole()) {
			if (iterable_element.getName().contains("USER")) {
				response.sendRedirect(request.getContextPath() + "/");
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
