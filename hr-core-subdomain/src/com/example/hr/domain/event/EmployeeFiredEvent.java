package com.example.hr.domain.event;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends HrBaseEvent {
	private final Employee employee;

	public EmployeeFiredEvent(Employee employee) {
		super(employee.getIdentity(), EventType.EMPLOYEE_FIRED);
		this.employee = employee;
	}

	public final Employee getEmployee() {
		return employee;
	}

}
