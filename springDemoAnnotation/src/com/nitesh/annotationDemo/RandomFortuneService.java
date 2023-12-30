package com.nitesh.annotationDemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	private String[] data = {
		"data1",
		"data2",
		"data3"
	};
	
	private Random myRandom = new Random();
	
	@Override
	public String getFortune() {
		// TODO Auto-generated method stub
		int index = myRandom.nextInt(data.length);
		return "RandomService : getFortune()"+ data[index];
	}

}
