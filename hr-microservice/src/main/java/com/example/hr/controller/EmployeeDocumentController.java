package com.example.hr.controller;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.repository.EmployeeDocumentRepository;

@Controller
@ConditionalOnProperty(name = "persistence", havingValue = "mongodb")
public class EmployeeDocumentController {
	private final EmployeeDocumentRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeDocumentController(EmployeeDocumentRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	@QueryMapping
	public EmployeeResponse employeeById(@Argument String identity) {
		return modelMapper.map(employeeRepository.findById(identity),EmployeeResponse.class);
	}
}
