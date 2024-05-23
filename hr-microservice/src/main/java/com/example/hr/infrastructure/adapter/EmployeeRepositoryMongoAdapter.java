package com.example.hr.infrastructure.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.ddd.Repository;
import com.example.hexagon.Adapter;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infrastructure.persistence.EmployeeRepository;
import com.example.hr.repository.EmployeeDocumentRepository;

@Service
@Repository(entity = Employee.class, id = String.class)
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistence", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository{
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository,
			ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean exists(TcKimlikNo identity) {
		return employeeDocumentRepository.existsById(identity.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var document = modelMapper.map(employee, EmployeeDocument.class);
		var insertedDocument = employeeDocumentRepository.insert(document);
		return modelMapper.map(insertedDocument, Employee.class);
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		return employeeDocumentRepository.findById(identity.getValue())
                                         .map(document -> modelMapper.map(document,Employee.class));
	}

	@Override
	public void removeEmployee(Employee employee) {
		employeeDocumentRepository.findById(employee.getIdentity().getValue())
                                  .ifPresent(employeeDocumentRepository::delete);		
	}

}
