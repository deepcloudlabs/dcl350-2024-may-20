package com.example.lottery.service;

import java.util.List;

import com.example.lottery.model.LotteryModel;

public interface LotteryService {
	public LotteryModel draw();

	public List<LotteryModel> draw(int column);
}
