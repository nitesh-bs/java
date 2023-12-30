package com.nitesh.springDemo;

public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		
		return "service fortune ";
	}

}
