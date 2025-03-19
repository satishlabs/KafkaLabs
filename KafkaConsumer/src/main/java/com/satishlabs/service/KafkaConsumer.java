package com.satishlabs.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "myTopic", groupId = "myGroup")
	public void listen(String message) {
		System.out.println("Received Message: "+message);
	}
	
}
