package com.nitesh.stayIn.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.exception.SuccessResponse;
import com.nitesh.stayIn.service.UserService;
import com.nitesh.stayIn.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	public UserService userService;

//	 private Set<String> skipUrls = new HashSet<>(Arrays.asList("/swagger-ui/"));
//	 private AntPathMatcher pathMatcher = new AntPathMatcher();
//
//
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		String path = request.getServletPath();
//	      return !path.matches("/swagger-ui/*");
//
////		System.out.println("uri : : : "+request.getRequestURI());
////	      return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
//
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/login")) {

		} else if (request.getServletPath().equals("/register")) {

		} else if (request.getServletPath().equals("/registerUser")) {

		} else if (request.getServletPath().equals("/signUp")) {

		} else if (request.getServletPath().startsWith("/v3/api-docs")) {

		} else if (request.getServletPath().startsWith("/swagger-ui/")) {

		} else if (request.getServletPath().equals("/favicon.ico")) {

		} else {

			response.setStatus(401);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println(request.getServletPath());

			String header = request.getHeader("Authorization");

			String jwtToken = null;
			String username = null;

			SuccessResponse successResponse = new SuccessResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid Token",
					System.currentTimeMillis(), null);

			if (header != null && header.startsWith("Bearer ")) {
				jwtToken = header.substring(7);

				if (jwtUtils.getJwtFromCookies(request).equals(jwtToken)) {

					try {
						username = jwtUtils.getUsernameFromToken(jwtToken);
					} catch (IllegalArgumentException e) {
						successResponse.setMessage("Unable to get token!");
						response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
					} catch (ExpiredJwtException e) {
						successResponse.setMessage("Jwt Token is expired!!!");
						response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
					} catch (Exception e) {
						successResponse.setMessage("Invalid Token!!!");
						response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
					}
				} else {
					successResponse.setMessage("Unauthorized Token!!!");
					response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
				}

//				try {
//					username = jwtUtils.getUsernameFromToken(jwtToken);
//					if (username == null) {
//						successResponse.setMessage("Invalid Token!");
//						response.sendError(400, new ObjectMapper().writeValueAsString(successResponse));
//					}
//				} catch (IllegalArgumentException e) {
//					successResponse.setMessage("Unable to get token!");
//					response.sendError(400, new ObjectMapper().writeValueAsString(successResponse));
//					// throw new BadRequestException("Unable to get token!");
//				} catch (ExpiredJwtException e) {
//					successResponse.setMessage("Jwt Token is expired!");
//					response.sendError(400, new ObjectMapper().writeValueAsString(successResponse));
//					// throw new BadRequestException("Jwt Token is expired!");
//				} catch (Exception e) {
//					System.out.println("Exception  ::::::::;Access is deined ::::::::"+request.getServletPath());
//					//successResponse.setMessage("Invalid Token!");
//					//response.sendError(400, new ObjectMapper().writeValueAsString(successResponse));
//					 throw new BadRequestException("Invalid Token!");
//				}

			} else {
				successResponse.setMessage("Token does not start with bearer!");
				response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
				// throw new BadRequestException("Token does not start with bearer");

			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userService.loadUserByUsername(username);

				if (jwtUtils.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}

		}

		filterChain.doFilter(request, response);

	}

}
