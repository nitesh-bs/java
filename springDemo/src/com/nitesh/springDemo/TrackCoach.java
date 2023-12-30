package com.nitesh.springDemo;

public class TrackCoach implements Coach , DisposableBean{

	@Override
	public String getDailyWorkOut() {
		// TODO Auto-generated method stub
		return "This is track coach workout method";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "track fortune";
	}

	public void getStartup()
	{
		System.out.println("Track : startup");
	}
	
	public void doDestroy() {
		System.out.println("Track : destroy");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("track : my cleanup destory");
	}
	
}
