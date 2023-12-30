package com.nitesh.annotationDemo;

import org.springframework.stereotype.Component;

@Component
public class Activity4Coach implements Coach {

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Activity 4 Coach : getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Activity 4 Coach : getDailyFortune()";
	}

}
