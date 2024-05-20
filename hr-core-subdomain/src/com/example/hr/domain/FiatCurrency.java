package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public enum FiatCurrency {
	TL("\u20ba"), USD("$"), EUR("€");
	private final String symbol;

	private FiatCurrency(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
}
