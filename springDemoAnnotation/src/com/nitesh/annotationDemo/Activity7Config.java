package com.nitesh.annotationDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Activity7Config {

	@Bean
	public FortuneService activity7Fortune() {
		return new Activity7FortuneService();
	}
	
	@Bean Coach getActivity7Coach() {
		return new Activity7Coach(activity7Fortune());
	}
	
}
