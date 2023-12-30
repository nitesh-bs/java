package com.nitesh.springDemo;

public class GolfCoach implements Coach {

	private FortuneService fortuneService;
	
	public GolfCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Practice your putting skills for 2 hours today";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}