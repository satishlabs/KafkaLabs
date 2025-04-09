package com.satishlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.kafka.Order;
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
	
	@GetMapping("/testOrder")
	public String placeOrder() {
		System.out.println("---TestController --- placeOrder()");
		Order myorder = new Order(5001, "9-April-2024", "C-101", 5, 500, "New");
		testService.sendOrder(myorder);
		myorder = new Order(5002, "5-April-2024", "C-102", 2, 1000, "Updated");
		testService.sendOrder(myorder);
		return "Order Sent to Kafka Server";
	}
}
