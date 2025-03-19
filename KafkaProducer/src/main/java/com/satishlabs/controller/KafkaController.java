package com.satishlabs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.service.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	private final KafkaProducer kafkaProducer;

	public KafkaController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@GetMapping("/send/{message}")
	public ResponseEntity<String> send(@PathVariable String message){
		kafkaProducer.sendMessage(message);
		return ResponseEntity.ok("Message Sent!");
	}
	
}
