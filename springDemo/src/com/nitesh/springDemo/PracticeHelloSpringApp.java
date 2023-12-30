package com.nitesh.springDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeHelloSpringApp {

	public static void main(String[] args) {

		// load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
				
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myGolfCoach", Coach.class);
		
		Coach theCoach2 = context.getBean("myGolfCoach", Coach.class);
		
		System.out.println("result : "+(theCoach == theCoach2));
		System.out.println(theCoach.getDailyWorkOut());
		System.out.println(theCoach.getDailyFortune());
		
		// close the context
		context.close();
	}

}
