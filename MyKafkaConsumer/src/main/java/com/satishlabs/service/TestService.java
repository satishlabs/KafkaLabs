package com.satishlabs.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@KafkaListener(topics = "myjlctopic", groupId = "myjlc_group")
	public void receiveMessage(String message) {
		System.out.println("---TestService --- receiveMessage() ---");
		System.out.println(message);
	}
	
}
