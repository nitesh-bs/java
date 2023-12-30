package com.nitesh.springActiveMQ.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "nitesh")
	public void listener(String message) {
		System.out.println("Received Message : "+message);
	}
}
