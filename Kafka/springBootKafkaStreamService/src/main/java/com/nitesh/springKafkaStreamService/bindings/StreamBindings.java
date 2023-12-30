package com.nitesh.springKafkaStreamService.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

import com.nitesh.springKafkaStreamService.model.Order;

public interface StreamBindings {

	@Input("order-input-channel")
	KStream<String, String> inputStream();
	
	@Output("order-takeaway-output-channel")
	KStream<String, String> takeAwayStream();
	
	@Output("order-homedelivery-output-channel")
	KStream<String, String> homeDeliveryStream();
	
	
}
