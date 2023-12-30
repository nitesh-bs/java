package com.nitesh.kafka.config;
import com.nitesh.kafka.SuperHero;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {
//	private static final String KAFKA_BROKER = "pkc-ldvr1.asia-southeast1.gcp.confluent.cloud:9092";
//
//	@Bean
//	public <K, V> ProducerFactory<K, V> createOrderProducerFactory(){
//		Map<String,Object> config = new HashMap<>();
//		config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
//		config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return new DefaultKafkaProducerFactory(config);
//	}
//
//	@Primary
//	@Bean
//	public <K, V> KafkaTemplate<String, SuperHero> createOrderKafkaTemplate(){
//		return new KafkaTemplate<>(createOrderProducerFactory());
//	}

}