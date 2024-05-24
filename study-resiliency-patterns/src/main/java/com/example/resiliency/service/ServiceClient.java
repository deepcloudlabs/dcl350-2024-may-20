package com.example.resiliency.service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ServiceClient {

	@Retry(name="service-a",fallbackMethod = "fallbackCallService")
	public int callService() {
		System.err.println("running callService() at %s".formatted(new Date()));
		if(ThreadLocalRandom.current().nextInt(100) < 60) {
			throw new IllegalStateException("Something is wrong!");
		}
		return 42;
	}
	
	public int fallbackCallService(Throwable t) {
		System.err.println("running fallbackCallService() at %s".formatted(new Date()));		
		return 108;
	}
}
