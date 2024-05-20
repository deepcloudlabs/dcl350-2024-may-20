package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valuesOf")
public record Photo(byte[] values) {

	public static Photo valuesOf(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}

	// helper factory method
	public static Photo valuesOf(String base64Values) {
		Objects.requireNonNull(base64Values);
		return new Photo(Base64.getDecoder().decode(base64Values));
	}

	public String base64Values() {
		return Base64.getEncoder().encodeToString(values);
	}

	@Override
	public String toString() {
		return "Photo [size=" + values.length + "]";
	}

}
