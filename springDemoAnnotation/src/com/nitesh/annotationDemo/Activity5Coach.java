package com.nitesh.annotationDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Activity5Coach implements Coach{

	@Autowired
	@Qualifier("activity5FortuneService")
	private FortuneService fortuneService;
	
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Activity 5 getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Activity 5 getDailyFortune() : "+ fortuneService.getFortune();
	}

}
