package com.nitesh.kafka;

import com.nitesh.kafka.config.KafkaListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1")
public class MessageController {
    @Autowired
    private KafkaListeners myTopicConsumer;
    private  KafkaTemplate<String, SuperHero> createOrderKafkaTemplate;

    private static final String TOPIC = "nitesh";

    public MessageController(KafkaTemplate<String, SuperHero> createOrderKafkaTemplate) {
        this.createOrderKafkaTemplate = createOrderKafkaTemplate;
    }

//    http://localhost:8080/api/v1/produce?message=Message sent by my App!.
    @GetMapping(value = "/produce")
    public String produce(@RequestParam String message) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, SuperHero>> sumon = createOrderKafkaTemplate.send(TOPIC, UUID.randomUUID().toString(), new SuperHero("Sumon", message));

        System.out.println("SendResult ::: sumon.toString() "+ sumon.get().toString());
        return message;
    }

    @GetMapping("/messages")
    public List<SuperHero> getMessages() {
        return myTopicConsumer.getMessages();
    }
}
