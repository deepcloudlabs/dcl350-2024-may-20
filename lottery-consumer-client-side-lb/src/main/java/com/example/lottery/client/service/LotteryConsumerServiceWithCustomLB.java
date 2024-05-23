package com.example.lottery.client.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name="loadBalancingStrategy", havingValue = "custom")
public class LotteryConsumerServiceWithCustomLB {
	private final static String LOTTERY_API_URL="http://%s:%d/api/v1/numbers?column=3";
	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;
	private final String lotteryServiceName;
	private List<ServiceInstance> instances;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public LotteryConsumerServiceWithCustomLB(RestTemplate restTemplate, DiscoveryClient discoveryClient, 
			@Value("${lotteryServiceName}") String lotteryServiceName) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
		this.lotteryServiceName = lotteryServiceName;
	}

	@PostConstruct
	@Scheduled(fixedRateString = "${instanceListRefreshPeriod}")
	public void initializeInstanceList() {
		instances = discoveryClient.getInstances(lotteryServiceName);
	}
	
	@Scheduled(fixedRate = 5_000)
	public void callLotteryService() {
		var index = counter.getAndIncrement() % instances.size();
		ServiceInstance serviceInstance = instances.get(index);
		var host = serviceInstance.getHost();
		var port = serviceInstance.getPort();
		try {
			var response = restTemplate.getForEntity(LOTTERY_API_URL.formatted(host,port), String.class).getBody();
			System.out.println(response);			
		}catch (Exception e) {
			initializeInstanceList();
		}
	}
}
