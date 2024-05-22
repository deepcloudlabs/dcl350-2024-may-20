package com.example.hr.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrBaseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "messaging", havingValue = "kafka-websocket")
public class KafkaHrEventListenerService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String topicName;

	public KafkaHrEventListenerService(KafkaTemplate<String, String> kafkaTemplate, @Value("${topicName}") String topicName,
			ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.topicName = topicName;
	}
	
	@EventListener
	public void listenHrEvent(HrBaseEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			System.err.println("Publishing event [%s] to topic [%s].".formatted(eventAsJson,topicName));
			kafkaTemplate.send(topicName, null, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}
}
