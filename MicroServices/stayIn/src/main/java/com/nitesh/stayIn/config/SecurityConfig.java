package com.nitesh.stayIn.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.stayIn.exception.SuccessResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private UserDetailsService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors();
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/register","/login").permitAll()
//		.antMatchers(HttpHeaders.ALLOW).permitAll()
//		.anyRequest().authenticated().and()
//		.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
//
//			@Override
//			public void commence(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException authException) throws IOException, ServletException {
//					response.sendError(403,authException.getMessage());
//
//			}
//		}).and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests();
		http.cors();
		http.csrf().disable().authorizeRequests()
				.antMatchers("/register", "/login", "/registerUser", "/signUp", "/v3/api-docs/**", "/v2/api-docs",
						"/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html")
				.permitAll().antMatchers(HttpHeaders.ALLOW).permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {

					@Override
					public void commence(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException authException) throws IOException, ServletException {
						System.out.println("security config ::: " + authException.getMessage());

//						SuccessResponse successResponse = new SuccessResponse(HttpStatus.BAD_REQUEST.value(),
//								"Invalid Token authenticationEntryPoint", System.currentTimeMillis(), null);
//						response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));

						// response.sendError(400, new
						// ObjectMapper().writeValueAsString(successResponse));
						// response.sendError(403,authException.getMessage());

//						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//						String message;
//						// Check if the request as any exception that we have stored in Request
//						final Exception exception = (Exception) request.getAttribute("exception");
//
//						// If yes then use it to create the response message else use the authException
//						if (exception != null) {
//
//							byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("cause", exception.toString()));
//							response.getOutputStream().write(body);
//						} else {
//							if (authException.getCause() != null) {
//								message = authException.getCause().toString() + " " + authException.getMessage();
//							} else {
//								message = authException.getMessage();
//							}
//
//							byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
//
//							response.getOutputStream().write(body);
//						}

					}
				}).accessDeniedHandler(new AccessDeniedHandler() {

					@Override
					public void handle(HttpServletRequest request, HttpServletResponse response,
							AccessDeniedException accessDeniedException) throws IOException, ServletException {
						System.out.println("security accessDeniedException ::: " + accessDeniedException.getMessage());

						SuccessResponse successResponse = new SuccessResponse(HttpStatus.UNAUTHORIZED.value(),
								accessDeniedException.getMessage(), System.currentTimeMillis(), null);
						response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));
					}
				}).and().logout().logoutUrl("/j_spring_security_logout").and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	 

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/ignore2");
//    }

//	@Autowired
//	public void configureGloble(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}

}