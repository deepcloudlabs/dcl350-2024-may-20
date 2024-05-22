package com.example.hr.controller;

import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.repository.EmployeeEntityRepository;

@Controller
public class EmployeeController {
	private final EmployeeEntityRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeController(EmployeeEntityRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	@QueryMapping
	public EmployeeResponse employeeById(@Argument String identity) {
		return modelMapper.map(employeeRepository.findById(identity),EmployeeResponse.class);
	}
}
