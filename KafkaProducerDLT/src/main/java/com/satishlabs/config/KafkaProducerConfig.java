package com.satishlabs.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

@SpringBootApplication
public class KafkaProducerConfig {

	@Bean
	public ProducerFactory<String, String> stringProducerFactory() {
		Map<String, Object> props = new HashMap<String, Object>();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.RETRIES_CONFIG, 3);

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, String> stringKafkaTemplate() {
	    KafkaTemplate<String, String> template = new KafkaTemplate<>(stringProducerFactory());
	    template.setProducerListener(new ProducerListener<>() {
	        @Override
	        public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata metadata) {
	            System.out.println(" Sent: " + producerRecord.value());
	        }

	        @Override
	        public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata metadata, Exception exception) {
	            System.err.println("Failed: " + exception.getMessage());
	        }
	    });
	    return template;
	}

}
