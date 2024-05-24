package com.example.crm.events;

public class CustomerReleasedEvent extends CrmBaseEvent {

	public CustomerReleasedEvent(String identity) {
		super(identity, EventType.CUSTOMER_RELEASED_EVENT);
	}

}
