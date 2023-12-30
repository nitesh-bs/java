package com.nitesh.springDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanScopeDemoApp {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScopeApplicationContext.xml");
				
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach theCoach2 = context.getBean("myCoach", Coach.class);
		
		boolean result = (theCoach == theCoach2);
		
		System.out.println("result : "+result);
		
		System.out.println("coach 1 " + theCoach);
		System.out.println("coach 2 " + theCoach2);
		
		// close the context
		context.close();
	}

}
