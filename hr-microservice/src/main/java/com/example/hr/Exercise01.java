package com.example.hr;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.hr.domain.JobStyle;
import com.example.hr.repository.EmployeeEntityRepository;

@Service
@ConditionalOnProperty(name = "persistence", havingValue = "mysql")
public class Exercise01 {
	private final EmployeeEntityRepository employeeEntityRepository;

	public Exercise01(EmployeeEntityRepository employeeEntityRepository) {
		this.employeeEntityRepository = employeeEntityRepository;
	}

	public void doBusiness() {
		this.employeeEntityRepository.bulGetir(1980, 2000)
		                             // LINQ + EF -> Criteria API 
		                             .stream() // LINQ -> Stream API
		                             .filter(emp -> emp.getSalary() > 50_000)
		                             .filter(emp -> emp.getJobStyle().equals(JobStyle.FREELANCE))
		                             .toList();
	}
}
