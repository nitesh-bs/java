package com.nitesh.springBootWebSocketChatRoom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springBootWebSocketChatRoom.models.Message;

@RestController
public class MessageController {

	@SendTo("/topic/return-to")
	@MessageMapping("/message")
	public Message getContent(@RequestBody Message message) {
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		return message;
	}
}
