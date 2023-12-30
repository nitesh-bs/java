package com.nitesh.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
    }

//	@Bean
//	CommandLineRunner commandLineRunner(KafkaTemplate<String ,String> kafkaTemplate){
//		return  args -> {
////			for(int i=0;i <= 10_000;i++) {
//				kafkaTemplate.send("nitesh", "hello nitesh!!! " );
////			}
//		};
//	}
}
