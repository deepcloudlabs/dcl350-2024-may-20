package com.example.securitycard.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiClientService {
	private final RestTemplate restTemplate;
	
	public RestApiClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	//@Scheduled(fixedRate = 3_000)
	public void callHrApi() {
		var response = restTemplate.getForEntity("http://localhost:4100/hr/api/v1/employees/11111111110", String.class)
		            .getBody();
		System.err.println(response);
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callHrApiAsync() {
		System.err.println("Before making async call...");
		callAsyncHrApi().thenAcceptAsync(response -> System.err.println(response.getBody()));
		System.err.println("After making async call...");
	}
	
	
	@Async
	CompletableFuture<ResponseEntity<String>> callAsyncHrApi(){
		return CompletableFuture.supplyAsync(() ->
			restTemplate.getForEntity("http://localhost:4100/hr/api/v1/employees/11111111110", String.class)
		);
	}
}
