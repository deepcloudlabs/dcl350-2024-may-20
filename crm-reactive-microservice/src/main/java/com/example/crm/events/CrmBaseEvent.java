package com.example.crm.events;

import java.time.ZonedDateTime;
import java.util.UUID;


public abstract class CrmBaseEvent {
	private final UUID uuid = UUID.randomUUID();
	private final ZonedDateTime timestamp = ZonedDateTime.now();
	private final Sequence sequence = Sequence.next();
	private final EventType eventType;
	private String identity;
	
	public CrmBaseEvent(String identity, EventType eventType) {
		this.identity = identity;
		this.eventType = eventType;
	}

	public UUID getUuid() {
		return uuid;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public String getIdentity() {
		return identity;
	}

	public final EventType getEventType() {
		return eventType;
	}

}
