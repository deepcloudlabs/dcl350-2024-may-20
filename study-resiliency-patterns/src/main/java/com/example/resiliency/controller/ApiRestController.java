package com.example.resiliency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
public class ApiRestController {

	@GetMapping
	@Bulkhead(name="get-resource", type = Type.THREADPOOL)
	public int getResource() {
		return 42;
	}
}
