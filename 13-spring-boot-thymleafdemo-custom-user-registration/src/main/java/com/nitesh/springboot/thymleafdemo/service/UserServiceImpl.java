package com.nitesh.springboot.thymleafdemo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitesh.springboot.thymleafdemo.dao.RoleDao;
import com.nitesh.springboot.thymleafdemo.dao.UserDao;
import com.nitesh.springboot.thymleafdemo.entity.Role;
import com.nitesh.springboot.thymleafdemo.entity.User;
import com.nitesh.springboot.thymleafdemo.user.CrnUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired(required = true)
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	@Transactional
	public void save(CrnUser crmuser) {
		User user = new User();
		
		user.setUserName(crmuser.getUserName());
		
		user.setPassword(passwordEncoder.encode(crmuser.getPassword()));
		
		user.setFirstName(crmuser.getFirstName());
		
		user.setLastName(crmuser.getLastName());
		
		user.setEmail(crmuser.getEmail());
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		userDao.save(user);
		
		
	}
	
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
