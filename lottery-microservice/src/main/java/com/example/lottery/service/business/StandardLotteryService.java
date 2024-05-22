package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.model.LotteryModel;
import com.example.lottery.service.LotteryService;

@Service
@RefreshScope
public class StandardLotteryService implements LotteryService {
	private final int lotteryMin;
	private final int lotteryMax;
	private final int lotterySize;
	
	public StandardLotteryService(
			@Value("${lotteryMin}") int lotteryMin, 
			@Value("${lotteryMax}") int lotteryMax, 
			@Value("${lotterySize}") int lotterySize) {
		this.lotteryMin = lotteryMin;
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	@Override
	public LotteryModel draw() {
		var numbers = ThreadLocalRandom.current()
				                       .ints(lotteryMin,lotteryMax)
				                       .distinct()
				                       .limit(lotterySize)
				                       .sorted()
				                       .boxed()
				                       .toList();
		return new LotteryModel(numbers);
	}

	@Override
	public List<LotteryModel> draw(int column) {
		return IntStream.range(0, column).mapToObj(i ->draw()).toList();
	}

}
