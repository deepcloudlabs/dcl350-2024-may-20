package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;

import jakarta.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name="persistence", havingValue = "mongodb")
public class ModelMapperMongoDocumentConfig {
	private static final  Converter<Employee,EmployeeDocument> EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = 
			context -> 	{
				var employee = context.getSource();
				var document = new EmployeeDocument();
				document.setIdentity(employee.getIdentity().getValue());
				document.setFirstName(employee.getFullName().firstName());
				document.setLastName(employee.getFullName().lastName());
				document.setIban(employee.getIban().getValue());
				document.setSalary(employee.getSalary().amount());
				document.setCurrency(employee.getSalary().currency());
				document.setDepartments(employee.getDepartments());
				document.setJobStyle(employee.getJobStyle());
				document.setBirthYear(employee.getBirthYear().year());
				document.setPhoto(employee.getPhoto().base64Values());
				return document;
			};	
			
	private static final  Converter<EmployeeDocument,Employee> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = 
			context -> 	{
				var document = context.getSource();
				return new Employee.Builder(document.getIdentity())
						           .fullName(document.getFirstName(), document.getLastName())
						           .iban(document.getIban())
						           .salary(document.getSalary(),document.getCurrency())
						           .departments(document.getDepartments())
						           .jobStyle(document.getJobStyle())
						           .birthYear(document.getBirthYear())
						           .photo(document.getPhoto())
						           .build();
			};	
	private final ModelMapper modelMapper;
	
	public ModelMapperMongoDocumentConfig(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	ModelMapper updateModelMapper() {		
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, EmployeeDocument.class, Employee.class);
		return modelMapper;
	}

}
