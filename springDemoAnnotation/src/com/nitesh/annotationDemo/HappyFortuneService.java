package com.nitesh.annotationDemo;

import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		// TODO Auto-generated method stub
		return "HappyFortuneService : getFortune()";
	}

}
