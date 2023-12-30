package com.nitesh.springKafkaStreamProducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springKafkaStreamProducer.model.Order;
import com.nitesh.springKafkaStreamProducer.service.KafkaProducerService;

@RestController
public class OrderController {

	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@PostMapping("/publish")
	public void order(@RequestBody Order order) {
		kafkaProducerService.send(order);
	}
}
