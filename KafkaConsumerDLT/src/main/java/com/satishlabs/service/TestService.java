package com.satishlabs.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @KafkaListener(topics = "myjlctopic", groupId = "myjlc_group", containerFactory = "stringKafkaListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println(">> Received message: " + message);
        // Add any processing logic or validation here
    }

    // Optional: If you want to handle messages that go to DLT
    @KafkaListener(topics = "myjlctopic.DLT", groupId = "dlt_group")
    public void dltListener(String message) {
        System.err.println("!! Message moved to DLT: " + message);
    }
}

