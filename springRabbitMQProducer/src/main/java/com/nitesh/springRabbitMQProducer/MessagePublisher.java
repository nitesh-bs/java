package com.nitesh.springRabbitMQProducer;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MessagePublisher {

	@Autowired
	private RabbitTemplate template;
	
	@GetMapping("/publish")
	public String publishMessage(@RequestBody CustomMessage message) {
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		template.convertAndSend(MqConfig.EXCHANGE,MqConfig.ROUTING_KEY,message);
		
		return "Message Published";
	}
}
