package com.nitesh.aopDemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;
import com.nitesh.aopDemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {

	private static Logger  myLogger = 
			Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

		String data = fortuneService.getFortune();
		
		myLogger.info("My Fortune is : "+data);
		
		myLogger.info("Finished");
		
		context.close();
	}
}
