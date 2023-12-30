package com.nitesh.springKafkaStreamService.service;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.nitesh.springKafkaStreamService.bindings.StreamBindings;
import com.nitesh.springKafkaStreamService.model.Order;

@EnableBinding(StreamBindings.class)
@Service
public class StreamService {

	@StreamListener("order-input-channel")
	@SendTo("order-takeaway-output-channel")
	public KStream<String, String> takeAway(KStream<String, String> order){
		
//		Object order2 = order.toString();
//		Order order3= (Order) order2;
//		if(order3.getDeliveryType().equals("takeway")) {
//			System.out.println("takeway------------");
//			return order;
//		}else {
//			return order;
//		}
		return order.toString().contains("takeaway")?order:order;
	}
	
	@StreamListener("order-input-channel")
	@SendTo("order-homedelivery-output-channel")
	public KStream<String, String> homeDelivery(KStream<String, String> order){
		
//		Object order2 = order.toString();
//		Order order3= (Order) order2;
//		if(order3.getDeliveryType().equals("COD")) {
//			return order;
//		}
//		else {
//			return order;
//		}
		return order.toString().contains("COD")?order:order;
	}
	
	
}
