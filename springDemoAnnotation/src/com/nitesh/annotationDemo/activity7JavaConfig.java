package com.nitesh.annotationDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class activity7JavaConfig {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Activity7Config.class);
		
		Coach coach = context.getBean("getActivity7Coach",Coach.class);
		
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailyFortune());
		
		
		context.close();
	}

}
