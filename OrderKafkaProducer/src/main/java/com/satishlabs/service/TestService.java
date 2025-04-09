package com.satishlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.satishlabs.kafka.Order;

@Service
public class TestService {
	
	@Autowired
	private KafkaTemplate<String, String> stringKafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, Order> orderKafkaTemplate;
	
	
	public void sendMessage(String message) {
		System.out.println("TestService -- sendMessage()");
		stringKafkaTemplate.send("myjlctopic",message);
	}
	
	public void sendOrder(Order myorder) {
		System.out.println("TestService -- sendOrder()");
		orderKafkaTemplate.send("myordertopic", myorder);
	}
}
