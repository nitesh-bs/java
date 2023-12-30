package com.nitesh.springDemo;

public class BaseballCoach implements Coach {

	
	private FortuneService fortuneService;
	
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}




	@Override
	public String getDailyWorkOut() {
		return "Spend 1 hour for betting";
	}




	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "baseball fortune";
	}

	
	
}
