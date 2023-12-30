package com.nitesh.annotationDemo;

public class Activity7Coach implements Coach {

	
	private FortuneService fortuneService;

	public Activity7Coach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Activity7Coach : getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Activity7Coach : getDailyFortune() : "+fortuneService.getFortune();
	}

}
