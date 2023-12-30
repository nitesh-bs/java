package com.nitesh.springBootwebfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Spring  WebFlux Example",
		version = "1.0",
		description = "WebFlux Demo"
		))
public class SpringBootWebfluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxDemoApplication.class, args);
	}

}
