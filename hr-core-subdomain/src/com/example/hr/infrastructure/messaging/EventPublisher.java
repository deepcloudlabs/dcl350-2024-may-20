package com.example.hr.infrastructure.messaging;

public interface EventPublisher<Event> {
	void publishEvent(Event event);
}
