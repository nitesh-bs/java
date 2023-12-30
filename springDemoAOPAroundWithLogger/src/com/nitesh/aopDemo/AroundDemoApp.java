package com.nitesh.aopDemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;
import com.nitesh.aopDemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

		String data = fortuneService.getFortune();
		
		System.out.println("My Fortune is : "+data);
		
		context.close();
	}
}
