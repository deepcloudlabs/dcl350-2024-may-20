package com.example.securitycard.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventListenerService {

	@KafkaListener(topics = "${topicName}", groupId = "security-card")
	public void listenHrEvents(String event) {
		System.err.println("New hr event from Kafka has arrived: %s".formatted(event));
	}
	
	@KafkaListener(topics = "crm-events", groupId = "security-card")
	public void listenCrmEvents(String event) {
		System.err.println("New crm event from Kafka has arrived: %s".formatted(event));
	}
}
