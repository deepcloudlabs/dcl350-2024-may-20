package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public record Money(double amount, FiatCurrency currency) {
	public Money valueOf(double amount, FiatCurrency currency) {
		Objects.requireNonNull(currency);
		if (amount < 0.0)
			throw new IllegalArgumentException("Money should have a positive value!");
		return new Money(amount, currency);
	}

}
