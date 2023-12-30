package com.nitesh.aopDemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFortune() {
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "TrafficFortuneService : getFortune() return";
		
	}
	public String getFortune(boolean tripWire) {
		
		if(tripWire)
			throw new RuntimeException("getFortune Exception : ");
		
		return "TrafficFortuneService : getFortune With Boolean Parameter";
	}
}
