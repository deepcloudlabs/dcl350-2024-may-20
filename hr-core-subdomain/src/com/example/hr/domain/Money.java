package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public record Money(double amount, FiatCurrency currency) {
	public static Money valueOf(double amount, FiatCurrency currency) {
		Objects.requireNonNull(currency);
		if (amount < 0.0)
			throw new IllegalArgumentException("Money should have a positive value!");
		return new Money(amount, currency);
	}

	public Money multiply(double ratio) {
		return Money.valueOf(this.amount*(1.0+ratio/100.0),this.currency);
	}

	public boolean lessThan(Money money) {
		return false;
	}

}
