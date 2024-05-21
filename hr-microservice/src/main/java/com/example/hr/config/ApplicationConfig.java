package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.event.HrBaseEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.example.hr.infrastructure.persistence.EmployeeRepository;

@Configuration
public class ApplicationConfig {

	@Bean
	HrApplication createHrApplication(EmployeeRepository employeeRepository,EventPublisher<HrBaseEvent> eventPublisher) {
		return new StandardHrApplication(employeeRepository, eventPublisher);
	}
}
