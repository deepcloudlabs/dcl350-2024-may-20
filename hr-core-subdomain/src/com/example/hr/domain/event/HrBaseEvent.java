package com.example.hr.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.ddd.DomainEvent;
import com.example.ddd.utils.Sequence;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrBaseEvent {
	private final UUID uuid = UUID.randomUUID();
	private final ZonedDateTime timestamp = ZonedDateTime.now();
	private final Sequence sequence = Sequence.next();
	private final TcKimlikNo identity;
	private final EventType eventType;
	
	public HrBaseEvent(TcKimlikNo identity, EventType eventType) {
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

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public final EventType getEventType() {
		return eventType;
	}

}
