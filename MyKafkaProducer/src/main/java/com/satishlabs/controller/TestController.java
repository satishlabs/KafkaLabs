package com.satishlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/testMsg")
	public String testMessage() {
		System.out.println("---TestController --- testMessage()");
		testService.sendMessage("Hello Guys --- This is message 1");
		testService.sendMessage("Hi Guys --- How is kafka");
		return "String Message Send to Kafka Server ";
	}
}
