package com.nitesh.springDemo;

public class BasketballCoach implements Coach {

	@Override
	public String getDailyWorkOut() {
		// TODO Auto-generated method stub
		return "basketball : getDailyWorkout";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "basketball : getDailyFortune";
	}

	public void doStartup() {
		System.out.println("basketball Startup");
	}
	
	public void doCleanup() {
		System.out.println("basketball Cleanup");
	}
}
