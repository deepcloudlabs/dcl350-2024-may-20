package com.example.hr.infrastructure.persistence;

import java.util.Optional;

import com.example.ddd.Repository;
import com.example.hexagon.Port;
import com.example.hexagon.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVEN_PORT)
@Repository(entity = Employee.class, id=TcKimlikNo.class)
public interface EmployeeRepository {

	boolean exists(TcKimlikNo identity);

	Employee persist(Employee employee);

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	void removeEmployee(Employee employee);

}
