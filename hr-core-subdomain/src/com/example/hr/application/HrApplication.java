package com.example.hr.application;

import java.util.Optional;

import com.example.hexagon.Port;
import com.example.hexagon.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(PortType.DRIVING_PORT)
public interface HrApplication {
	Employee hireEmployee(Employee employee);

	Employee fireEmployee(TcKimlikNo identity);

	Optional<Employee> getEmployee(TcKimlikNo identity);

}
