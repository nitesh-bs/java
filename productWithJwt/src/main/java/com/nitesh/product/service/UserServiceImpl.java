package com.nitesh.product.service;

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

import com.nitesh.product.dao.RoleDao;
import com.nitesh.product.dao.UserDao;
import com.nitesh.product.entity.Role;
import com.nitesh.product.entity.User;
import com.nitesh.product.model.CrnUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username and Password!!!!!!!!!!!!!!!");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public void saveUser(CrnUser crmuser) {
		User user = new User();

		user.setUserName(crmuser.getUserName());

		user.setPassword(passwordEncoder.encode(crmuser.getPassword()));

		user.setFirstName(crmuser.getFirstName());

		user.setLastName(crmuser.getLastName());

		user.setEmail(crmuser.getEmail());
		user.setRole(Arrays.asList(roleDao.findRoleByRoleName("ROLE_USER")));

		userDao.saveUser(user);

	}

}
