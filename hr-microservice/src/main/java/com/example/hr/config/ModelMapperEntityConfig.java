package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.entity.EmployeeEntity;

import jakarta.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name="persistence", havingValue = "mysql")
public class ModelMapperEntityConfig {
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
			
	private final ModelMapper modelMapper;
	
	public ModelMapperEntityConfig(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	ModelMapper updateModelMapper() {		
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		return modelMapper;
	}

}
