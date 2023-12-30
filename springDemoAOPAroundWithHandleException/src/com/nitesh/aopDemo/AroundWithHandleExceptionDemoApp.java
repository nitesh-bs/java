package com.nitesh.aopDemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nitesh.aopDemo.dao.AccountDAO;
import com.nitesh.aopDemo.dao.MembershipDAO;
import com.nitesh.aopDemo.service.TrafficFortuneService;

public class AroundWithHandleExceptionDemoApp {

	private static Logger  myLogger = 
			Logger.getLogger(AroundWithHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);

		boolean tripWire = true;
		String data = fortuneService.getFortune(true);
		
		myLogger.info("My Fortune is : "+data);
		
		myLogger.info("Finished");
		
		context.close();
	}
}
