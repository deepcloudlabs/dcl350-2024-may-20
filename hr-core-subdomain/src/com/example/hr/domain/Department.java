package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public enum Department {
	SALES, HR, IT, FINANCE
}
