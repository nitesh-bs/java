package com.nitesh.annotationDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

//		Coach coach = context.getBean("swimCoach",Coach.class);
		Coach coach = context.getBean("activity5Coach",Coach.class);
//		Coach coach = context.getBean("tennisCoach",Coach.class);
		
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());
		
		context.close();
	}

}
