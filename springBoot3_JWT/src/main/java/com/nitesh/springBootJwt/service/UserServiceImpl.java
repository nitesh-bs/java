package com.nitesh.springBootJwt.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nitesh.springBootJwt.entity.Role;
import com.nitesh.springBootJwt.entity.User;
import com.nitesh.springBootJwt.exception.UserNotFoundException;
import com.nitesh.springBootJwt.repository.RoleRepository;
import com.nitesh.springBootJwt.repository.UserRepository;
import com.nitesh.springBootJwt.response.LoginResponse;
import com.nitesh.springBootJwt.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and password!!!");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));
	}

	@Override
	public User registerUser(User user) {

		System.out.println("User :::" + user);
		User isexistsUser = userRepository.findByUsername(user.getUsername());
		if (isexistsUser != null) {
			throw new RuntimeException("Username alreay exists!!!");
		}

		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);

	}

	@Override
	public LoginResponse signIn(User user) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (DisabledException e) {
			throw new RuntimeException("User is disable");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Bad Credentials from User");
		}
		
		UserDetails userdetails = loadUserByUsername(user.getUsername());
		
		String token = jwtUtil.generateToken(userdetails);
		
		return new LoginResponse( userRepository.findByUsername(user.getUsername()), token);
	}

}
