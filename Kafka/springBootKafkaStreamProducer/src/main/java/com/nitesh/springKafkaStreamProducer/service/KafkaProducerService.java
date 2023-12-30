package com.nitesh.springKafkaStreamProducer.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nitesh.springKafkaStreamProducer.model.Order;

@Service
public class KafkaProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
	
	private static final String TOPIC ="nitesh";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void send(Order order) {
		LOGGER.info(String.format("Order : {}", order));
		kafkaTemplate.send(TOPIC,UUID.randomUUID().toString(),order);
	}
}
