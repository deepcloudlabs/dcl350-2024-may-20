package com.example.hr.infrastructure.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.ddd.Repository;
import com.example.hexagon.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.infrastructure.persistence.EmployeeRepository;
import com.example.hr.repository.EmployeeEntityRepository;

@Service
@Repository(entity = Employee.class, id = String.class)
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistence", havingValue = "mysql")
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
		System.err.println(employeeEntityRepository.getClass().getName());
	}

	@Override
	public boolean exists(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Employee persist(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		var persistedEntity = employeeEntityRepository.save(entity);
		return modelMapper.map(persistedEntity, Employee.class);
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		return employeeEntityRepository.findById(identity.getValue())
				                       .map(entity -> modelMapper.map(entity,Employee.class));
	}

	@Override
	//@Transactional
	public void removeEmployee(Employee employee) {
		employeeEntityRepository.findById(employee.getIdentity().getValue())
		                        .ifPresent(employeeEntityRepository::delete);			
	}

}
