package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Inmemory User
		/*
		 * UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		 * 
		 * auth.inMemoryAuthentication()
		 * .withUser(userBuilder.username("nitesh").password("Admin123").roles(
		 * "Employee","Admin"))
		 * .withUser(userBuilder.username("jay").password("Admin123").roles("Employee",
		 * "Admin"))
		 * .withUser(userBuilder.username("raj").password("Admin123").roles("Employee",
		 * "Manager"));
		 */
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				
				  .antMatchers("/").permitAll() // allow public access to landing page
				 
		.antMatchers("/employees").hasRole("Employee")
		.antMatchers("/leaders/**").hasRole("Manager")
		.antMatchers("/system/**").hasRole("Admin")
		 .antMatchers("/css/**").permitAll()
				/* .anyRequest().authenticated() */
		.and()
		.formLogin()
		.loginPage("/showLoginPage")
		.loginProcessingUrl("/authenticateUser")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
