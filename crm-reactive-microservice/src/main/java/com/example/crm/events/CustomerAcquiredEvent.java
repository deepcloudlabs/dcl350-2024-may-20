package com.example.crm.events;

public class CustomerAcquiredEvent extends CrmBaseEvent {

	public CustomerAcquiredEvent(String identity) {
		super(identity, EventType.CUSTOMER_ACQUIRED_EVENT);
	}

}
