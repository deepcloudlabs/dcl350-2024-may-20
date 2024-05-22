package com.example.hr.infrastructure.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagon.Adapter;
import com.example.hr.domain.event.HrBaseEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;

@Service
@Adapter(port = EventPublisher.class)
@ConditionalOnProperty(name = "messaging", havingValue = "kafka-websocket")
public class EventPublisherSpringApplicationEventPublisherAdapter implements EventPublisher<HrBaseEvent> {
	private final ApplicationEventPublisher eventPublisher;
	
	public EventPublisherSpringApplicationEventPublisherAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publishEvent(HrBaseEvent event) {
		eventPublisher.publishEvent(event);
	}

}
