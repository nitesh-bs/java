package com.nitesh.springBoot3SecurityCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    InMemory
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails userDetails = User.builder()
//                .username("admin")
//                .password("{noop}admin") // no-operation so without encryption data store
//                .build();
//        UserDetails userDetails1 = User.builder()
//                .username("admin")
//                .password("{bcrypt}admin")  // it will users bcrypt algorithm to encoding
//                .build();
//
//        return  new InMemoryUserDetailsManager(userDetails,userDetails1);
//    }

    //add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,authority from authorities where username=?");
        return jdbcUserDetailsManager;

    }






    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/api/jpa/employee").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/jpa/employee/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/jpa/employee").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,"/api/jpa/employee").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE,"/api/jpa/employee/**").hasRole("ADMIN")

        );

        http.httpBasic();

        http.csrf().disable();

        return  http.build();
    }




}
