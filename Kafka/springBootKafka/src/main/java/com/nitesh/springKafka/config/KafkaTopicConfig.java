package com.nitesh.springKafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic niteshTopic() {
		return TopicBuilder.name("nitesh")
//				.partitions(6)
//				.replicas(3)
				.build();
	}
	
	@Bean
	public NewTopic niteshJsonTopic() {
		return TopicBuilder.name("nitesh_json")
//				.partitions(6)
//				.replicas(3)
				.build();
	}
}
