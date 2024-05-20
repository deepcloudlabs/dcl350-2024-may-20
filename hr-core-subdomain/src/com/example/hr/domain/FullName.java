package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public record FullName(String firstName, String lastName) {
	public FullName(String firstName, String lastName) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static FullName valueOf(String firstName, String lastName) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		return new FullName(firstName, lastName);
	}
}
