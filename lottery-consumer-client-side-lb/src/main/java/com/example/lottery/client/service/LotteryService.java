package com.example.lottery.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.model.LotteryModel;

@FeignClient(name = "lottery", path = "/api/v1/numbers")
public interface LotteryService {
	@GetMapping(params = "column")
	public List<LotteryModel> getNumbers(@RequestParam int column);

}
