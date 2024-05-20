package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject(factory = "valueOf")
public enum JobStyle {
	FULL_TIME, PART_TIME, FREELANCE, INTERN
}
