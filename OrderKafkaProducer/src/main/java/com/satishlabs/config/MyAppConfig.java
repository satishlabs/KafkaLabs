package com.satishlabs.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.satishlabs.kafka.Order;

@SpringBootApplication
public class MyAppConfig {

	@Bean
	public ProducerFactory<String, String> stringProducerFactory(){
		System.out.println("---stringProducerFactory---");
		Map<String, Object> props = new HashMap<String, Object>();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public ProducerFactory<String, Order> orderProducerFactory(){
		System.out.println("---orderProducerFactory---");
		Map<String, Object> props = new HashMap<String, Object>();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean(name = "stringKafkaTemplate")
	public KafkaTemplate<String, String> stringKafkaTemplate(){
		System.out.println("---stringKafkaTemplate---");
		return new KafkaTemplate<>(stringProducerFactory());
	}
	
	@Bean(name = "orderKafkaTemplate")
	public KafkaTemplate<String, Order> orderKafkaTemplate(){
		System.out.println("---orderKafkaTemplate---");
		return new KafkaTemplate<>(orderProducerFactory());
	}
}
