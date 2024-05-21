package com.example.ddd.utils;

import java.util.concurrent.atomic.AtomicLong;

public record Sequence(long value) {
	private static AtomicLong counter = new AtomicLong();

	public static Sequence next() {
		return new Sequence(counter.incrementAndGet());
	}
}
