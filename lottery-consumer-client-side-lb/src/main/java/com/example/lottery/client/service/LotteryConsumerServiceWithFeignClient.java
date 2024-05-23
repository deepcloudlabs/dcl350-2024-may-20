package com.example.lottery.client.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="loadBalancingStrategy", havingValue = "feign")
public class LotteryConsumerServiceWithFeignClient {
	private final LotteryService lotteryService;
	
	public LotteryConsumerServiceWithFeignClient(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 5_000)
	public void callLotteryService() {
		var response = lotteryService.getNumbers(3);
		System.out.println(response);
	}
}
