package com.nitesh.springDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanLifecycleDemoApp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanLifecycleApplicationContext.xml");
				
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoachTrack", Coach.class);
		Coach theCoach2 = context.getBean("myCoachTrack", Coach.class);
		
		boolean result = (theCoach == theCoach2);
		
		System.out.println("result " + result);
		System.out.println("coach " + theCoach.getDailyFortune());
		System.out.println("coach  " + theCoach2.getDailyWorkOut());
		
		
		// close the context
		context.close();
	}

}
