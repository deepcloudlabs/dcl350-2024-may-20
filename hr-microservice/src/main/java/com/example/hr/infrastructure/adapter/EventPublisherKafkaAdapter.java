package com.example.hr.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagon.Adapter;
import com.example.hr.domain.event.HrBaseEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Adapter(port = EventPublisher.class)
@ConditionalOnProperty(name = "messaging", havingValue = "kafka")
public class EventPublisherKafkaAdapter implements EventPublisher<HrBaseEvent> {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String topicName;

	public EventPublisherKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate, @Value("${topicName}") String topicName,
			ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.topicName = topicName;
	}

	@Override
	@Transactional
	public void publishEvent(HrBaseEvent event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			System.err.println("Publishing event [%s] to topic [%s].".formatted(eventAsJson,topicName));
			kafkaTemplate.send(topicName, null, eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}

}
