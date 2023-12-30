package com.nitesh.springKafkaStreamService;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.nitesh.springKafkaStreamService.model.Order;

@Configuration
public class KafkaProducerConfig {

//	private String bootStrapServer = "localhost:9092";
//	
////	@Bean
////	public KafkaTemplate<String, Order> kafkaTemplate(){
////		return new KafkaTemplate<>(consumerFactory());
////	}
//	
//	   @Bean
//	    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory() {
//	        ConcurrentKafkaListenerContainerFactory<String, Order>  
//	          factory = new ConcurrentKafkaListenerContainerFactory<>();
//	        factory.setConsumerFactory(consumerFactory());
//	        return factory;
//	    }
//
////	@Bean
////	public ProducerFactory<String, Order> producerfactory() {
////		Map<String, Object> config = new HashMap<>();
////		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
////		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////		
////		return new DefaultKafkaProducerFactory<>(config);
////	}
////	
//	   @Bean
//	    public ConsumerFactory<String, Order> consumerFactory() {
//	        Map<String, Object> configs = new HashMap<>();
//	        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
//	        		bootStrapServer);
//	        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
//	                                   StringDeserializer.class);
//	        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
//	                                   JsonSerializer.class);
//	        return new DefaultKafkaConsumerFactory<>(configs);
//	    }
}
