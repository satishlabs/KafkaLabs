package com.satishlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private KafkaTemplate<String, String> stringKafkaTemplate;
	
	public void sendMessage(String message) {
		System.out.println("TestService -- sendMessage()");
		stringKafkaTemplate.send("myjlctopic",null,message);
	}
}
