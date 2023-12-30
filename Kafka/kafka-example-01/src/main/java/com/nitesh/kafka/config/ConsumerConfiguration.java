package com.nitesh.kafka.config;

import com.nitesh.kafka.SuperHero;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {
//
//    private static final String KAFKA_BROKER = "pkc-ldvr1.asia-southeast1.gcp.confluent.cloud:9092";
//    private static final String GROUP_ID = "kafka-sandbox";
//
//    public Map<String, Object> consumerConfig() {
//        Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, SuperHero> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),
//                new JsonDeserializer<>(SuperHero.class));
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SuperHero>> factory(
//    ) {
//        ConcurrentKafkaListenerContainerFactory<String, SuperHero> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//
//    }
}