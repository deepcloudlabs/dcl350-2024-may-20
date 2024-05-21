package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrBaseEvent {

	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity, EventType.EMPLOYEE_HIRED);
	}

}
