package com.nitesh.springDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class beanActivity3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beanActivity3Context.xml");
		
		Coach coach = context.getBean("myCoach",Coach.class);
		
		Coach coach2 = context.getBean("myCoach",Coach.class);
		
		System.out.println((coach == coach2));
		
		System.out.println(coach.getDailyFortune());
		System.out.println(coach.getDailyWorkOut());
		
		context.close();
	}

}
