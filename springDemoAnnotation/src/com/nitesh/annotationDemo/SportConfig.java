package com.nitesh.annotationDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.nitesh.annotationDemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {

	
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	//define bean and inject dependency
	@Bean
	public Coach swimCoach() {
		
		return new SwimCoach(sadFortuneService());
	}
}
