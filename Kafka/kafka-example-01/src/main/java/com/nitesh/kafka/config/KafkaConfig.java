package com.nitesh.kafka.config;

import com.nitesh.kafka.SuperHero;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

//    @Bean
//    public KafkaTemplate kafkaTemplate() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-ldvr1.asia-southeast1.gcp.confluent.cloud:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-ldvr1.asia-southeast1.gcp.confluent.cloud:9092");
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, SuperHero.class);
//
//        DefaultKafkaProducerFactory<String, SuperHero> producerFactory = new DefaultKafkaProducerFactory<>(configProps);
//        DefaultKafkaConsumerFactory<String, SuperHero> consumerFactory = new DefaultKafkaConsumerFactory<>(configProps);
//
//        return new KafkaTemplate<>(producerFactory);
//    }
}