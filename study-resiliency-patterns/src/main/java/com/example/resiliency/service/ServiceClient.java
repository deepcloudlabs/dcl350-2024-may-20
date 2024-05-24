package com.example.resiliency.service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

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
	
	@RateLimiter(name="service-b")
	@Retry(name="service-b",fallbackMethod = "fallbackCallService")
	public int callRemoteService() {
		return 549;
	}
	
	@TimeLimiter(name="service-c",fallbackMethod = "fallBackAnotherRemoteService")
	public CompletableFuture<Integer> callAnotherRemoteService(){
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3, 12));}catch (Exception e) {}
			return 3615;
		});
	}
	public CompletableFuture<Integer> fallBackAnotherRemoteService(Throwable t){
		return CompletableFuture.supplyAsync(() -> {
			return 4629;
		});
	}
	
	
}
