package com.example.hr.exercises;

import com.example.hr.domain.FiatCurrency;

@SuppressWarnings("unused")
public class Exercise01 {

	public static void main(String[] args) {
		var euro = FiatCurrency.EUR;
		var usd = FiatCurrency.valueOf("USD");
		for (var currency : FiatCurrency.values()) {
			System.out.println("%s: %s,%d".formatted(currency.getSymbol(),currency.name(),currency.ordinal()));
		}
	}

}
