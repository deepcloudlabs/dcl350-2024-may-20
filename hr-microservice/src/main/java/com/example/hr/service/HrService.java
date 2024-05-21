package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ddd.OHS;
import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
@OHS
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;

	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Cacheable
	public EmployeeResponse findById(String identity) {
		var employee = hrApplication.getEmployee(TcKimlikNo.of(identity))
				                    .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exits!"));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		// object-to-object mapping: HireEmployeeRequest --> Employee
		var employee = modelMapper.map(request, Employee.class);
		// object-to-object mapping: Employee --> HireEmployeeResponse
		var hiredEmployee = hrApplication.hireEmployee(employee);
		return modelMapper.map(hiredEmployee, HireEmployeeResponse.class);
	}

	@Transactional
	public FireEmployeeResponse fireEmployee(String identity) {
		return modelMapper.map(hrApplication.fireEmployee(TcKimlikNo.of(identity)),FireEmployeeResponse.class);
	}

}
