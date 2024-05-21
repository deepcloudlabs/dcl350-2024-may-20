package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeFiredEvent;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.domain.event.HrBaseEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.example.hr.infrastructure.persistence.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrBaseEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository,EventPublisher<HrBaseEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getIdentity();
		if(employeeRepository.exists(identity))
			throw new IllegalArgumentException("Employee [%s] already exists.".formatted(identity.getValue()));
		// put all business process steps here
		
		// finally persisting the employee
		Employee persistedEmployee = employeeRepository.persist(employee);
		var event = new EmployeeHiredEvent(employee.getIdentity()); 
		eventPublisher.publishEvent(event);
		return persistedEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo identity) {
		var employee = employeeRepository.findEmployeeByIdentity(identity)
				                         .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exist.".formatted(identity.getValue())));
		// put all business process steps here
		
		// finally removing the employee
		employeeRepository.removeEmployee(employee);
		var event = new EmployeeFiredEvent(employee); 
		eventPublisher.publishEvent(event);
		return employee;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

}
