package com.nitesh.kafka.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "nitesh",
            groupId = "groudId"
    )
    void listener(String data) {
        System.out.println("Listener received :: " + data);
    }
}
