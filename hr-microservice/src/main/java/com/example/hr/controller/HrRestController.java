package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hexagon.Adapter;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

// REST over HTTP
@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
@Adapter(port = RestController.class)
public class HrRestController {
	private final HrService hrService;
	
	// constructor injection
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
		System.err.println(hrService.getClass().getName());
	}

	// GET http://localhost:4100/hr/api/v1/employees/11111111110
	@GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return hrService.findById(identity);
	}
	
	// POST http://localhost:4100/hr/api/v1/employees
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	// DELETE http://localhost:4100/hr/api/v1/employees/11111111110
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
	}
}
