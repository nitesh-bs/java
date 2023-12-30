package com.nitesh.kafka.config;

import com.nitesh.kafka.SuperHero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaListeners {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    private final List<SuperHero> messages = new ArrayList<>();

    @KafkaListener(topics = "nitesh", groupId = "kafka-sandbox")
    public void listen(SuperHero message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println(message+" ::: partition ::: "+ partition);
    }

    public List<SuperHero> getMessages() {
        return messages;
    }

}
