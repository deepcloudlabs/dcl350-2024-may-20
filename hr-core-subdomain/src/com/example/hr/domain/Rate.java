package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject(factory = "of")
public record Rate(double ratio) {
	public static Rate of(double ratio) {
		if (ratio <= 0.0)
			throw new IllegalArgumentException("Ration must be a positive value");
		return new Rate(ratio);
	}
}
