package com.nitesh.product.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitesh.product.entity.JwtRequest;
import com.nitesh.product.entity.JwtResponse;
import com.nitesh.product.entity.User;
import com.nitesh.product.util.Jwt;


@Service
public class JwtService  {
	
	@Autowired
	private com.nitesh.product.dao.UserDao userDao;
	
	@Autowired
	private Jwt jwtUtil;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		
		authenticate(userName, userPassword);
		
		final UserDetails userDetails = userService.loadUserByUsername(userName);
		
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		User user = userDao.findUserByUserName(userName);
		return new JwtResponse(user, newGeneratedToken);
	}


	private void authenticate(String userName,String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));	
		} catch (DisabledException e) {
			throw new Exception("User is disable");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials from User");
		}
		
	}
	
}
