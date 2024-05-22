package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final  Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = 
		context -> 	{
			var request = context.getSource();
			return new Employee.Builder(request.getIdentity())
					           .fullName(request.getFirstName(), request.getLastName())
					           .iban(request.getIban())
					           .salary(request.getSalary(),request.getCurrency())
					           .departments(request.getDepartments())
					           .jobStyle(request.getJobStyle())
					           .birthYear(request.getBirthYear())
					           .photo(request.getPhoto())
					           .build();
		};
		
	private static final  Converter<Employee,HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER = 
		context -> 	{
			var employee = context.getSource();
			var response = new HireEmployeeResponse();
			response.setIdentity(employee.getIdentity().getValue());
			response.setFirstName(employee.getFullName().firstName());
			response.setLastName(employee.getFullName().lastName());
			response.setIban(employee.getIban().getValue());
			response.setSalary(employee.getSalary().amount());
			response.setCurrency(employee.getSalary().currency());
			response.setDepartments(employee.getDepartments());
			response.setJobStyle(employee.getJobStyle());
			response.setBirthYear(employee.getBirthYear().year());
			return response;
		};	
		
	private static final  Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = 
			context -> 	{
				var employee = context.getSource();
				var response = new EmployeeResponse();
				response.setIdentity(employee.getIdentity().getValue());
				response.setFirstName(employee.getFullName().firstName());
				response.setLastName(employee.getFullName().lastName());
				response.setIban(employee.getIban().getValue());
				response.setSalary(employee.getSalary().amount());
				response.setCurrency(employee.getSalary().currency());
				response.setDepartments(employee.getDepartments());
				response.setJobStyle(employee.getJobStyle());
				response.setBirthYear(employee.getBirthYear().year());
				response.setPhoto(employee.getPhoto().base64Values());
				return response;
			};		
	
	private static final  Converter<Employee,FireEmployeeResponse> EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER = 
			context -> 	{
				var employee = context.getSource();
				var response = new FireEmployeeResponse();
				response.setIdentity(employee.getIdentity().getValue());
				response.setFirstName(employee.getFullName().firstName());
				response.setLastName(employee.getFullName().lastName());
				response.setIban(employee.getIban().getValue());
				response.setSalary(employee.getSalary().amount());
				response.setCurrency(employee.getSalary().currency());
				response.setDepartments(employee.getDepartments());
				response.setJobStyle(employee.getJobStyle());
				response.setBirthYear(employee.getBirthYear().year());
				response.setPhoto(employee.getPhoto().base64Values());
				return response;
			};		

			
	private static final  Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = 
			context -> 	{
				var employee = context.getSource();
				var response = new EmployeeEntity();
				response.setIdentity(employee.getIdentity().getValue());
				response.setFirstName(employee.getFullName().firstName());
				response.setLastName(employee.getFullName().lastName());
				response.setIban(employee.getIban().getValue());
				response.setSalary(employee.getSalary().amount());
				response.setCurrency(employee.getSalary().currency());
				response.setDepartments(employee.getDepartments());
				response.setJobStyle(employee.getJobStyle());
				response.setBirthYear(employee.getBirthYear().year());
				response.setPhoto(employee.getPhoto().values());
				return response;
			};	
			
	private static final  Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = 
			context -> 	{
				var entity = context.getSource();
				return new Employee.Builder(entity.getIdentity())
						           .fullName(entity.getFirstName(), entity.getLastName())
						           .iban(entity.getIban())
						           .salary(entity.getSalary(),entity.getCurrency())
						           .departments(entity.getDepartments())
						           .jobStyle(entity.getJobStyle())
						           .birthYear(entity.getBirthYear())
						           .photo(entity.getPhoto())
						           .build();
			};	
			
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, FireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		return modelMapper;
	}
}
