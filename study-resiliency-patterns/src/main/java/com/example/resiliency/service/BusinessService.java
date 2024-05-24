package com.example.resiliency.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	private final ServiceClient client;

	public BusinessService(ServiceClient client) {
		this.client = client;
	}

	// @Scheduled(fixedRate = 5_000)
	public void callService() {
		var result = client.callService();
		System.out.println("result is %d".formatted(result));
	}

	// @Scheduled(fixedRate = 100)
	public void callRemoteService() {
		var result = client.callRemoteService();
		System.out.println("result is %d".formatted(result));
	}

	@Scheduled(fixedRate = 5_000)
	public void callAnotherRemoteService() {
		client.callAnotherRemoteService()
				.thenAcceptAsync(result -> System.out.println("result is %d".formatted(result)));
	}
}
