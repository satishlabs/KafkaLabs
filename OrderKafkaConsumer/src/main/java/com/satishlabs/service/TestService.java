package com.satishlabs.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.satishlabs.kafka.Order;

@Service
public class TestService {
	
	@KafkaListener(topics = "myjlctopic", groupId = "myjlc_group", containerFactory = "stringKafkaListenerContainerFactory")
	public void receiveMessage(String message) {
		System.out.println("---TestService --- receiveMessage() ---");
		System.out.println(message);
	}
	
	@KafkaListener(topics = "myordertopic", groupId = "myorder_group", containerFactory = "orderKafkaListenerContainerFactory")
	public void receiveOrder(Order myorder) {
		System.out.println("---TestService --- receiveOrder() ---");
		System.out.println(myorder);
	}
	
}
