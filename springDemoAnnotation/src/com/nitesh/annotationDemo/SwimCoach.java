package com.nitesh.annotationDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
public class SwimCoach implements Coach {

	@Value("${foo.email}")
	private String email;

	@Value("${foo.name}")
	private String team;

	private FortuneService fortuneService;

	public SwimCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "SwimCoach : getDailyWorkout()";
	}

	/*
	 * @Override public String getDailyFortune() { // TODO Auto-generated method
	 * stub return "SwimCoach : getDailyWorkout() : "+team +" - "+email; }
	 */

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return team +" - "+email
				+ "SwimCoach : getDailyFortune() : " + fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}

	public FortuneService getFortuneService() {
		return fortuneService;
	}
	
	

}
