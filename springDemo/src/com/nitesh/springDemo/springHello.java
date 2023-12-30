package com.nitesh.springDemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class springHello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext contexts
		= new ClassPathXmlApplicationContext("applicationContext.xml");

		/* Coach coach= contexts.getBean("myCoach",Coach.class); */
		CricketCoach coach= contexts.getBean("myCoach",CricketCoach.class);
		System.out.println(coach.getDailyWorkOut());	
		System.out.println(coach.getDailyFortune());	
		System.out.println(coach.getEmail()+ " - "+coach.getTeam() +" = ");
		contexts.close();
	}

}
