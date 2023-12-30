package com.nitesh.springDemo;

public class CricketCoach implements Coach{

	private FortuneService fortuneService;
	
	private String  email,team;
	
	
	public CricketCoach() {
		System.out.println("cricket default constructor");
	}
	
	
	

	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getTeam() {
		return team;
	}




	public void setTeam(String team) {
		this.team = team;
	}




	public FortuneService getFortuneService() {
		return fortuneService;
	}



	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}



	@Override
	public String getDailyWorkOut() {

		return "Cricket coach";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "cricket fortune";
	}

}
