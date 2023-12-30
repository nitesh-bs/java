package com.nitesh.annotationDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwinJavaConfigDemoApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
//		Coach coach = context.getBean("swimCoach",Coach.class);
		SwimCoach coach = context.getBean("swimCoach",SwimCoach.class);
		
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());
		System.out.println(coach.getEmail() + coach.getTeam());
		
		
		context.close();
	}

}
