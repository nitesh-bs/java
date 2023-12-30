package com.nitesh.annotationDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");

		Coach coach = context.getBean("tennisCoach",Coach.class);
		
		/*
		 * Coach coach2 = context.getBean("tennisCoach",Coach.class);
		 * 
		 * System.out.println("result : "+(coach == coach2));
		 */
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());
		
		context.close();
	}
}
