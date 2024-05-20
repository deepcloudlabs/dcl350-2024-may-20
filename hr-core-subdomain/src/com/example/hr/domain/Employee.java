package com.example.hr.domain;

import java.util.List;

import com.example.ddd.Entity;
// Problem Space   -> Solution Space
// Core Sub-domain -> Bounded Context -> Ubiquitous Language
// Entity: i) persistent ii) identity
@Entity(identity="identity")
public class Employee {
	private TcKimlikNo identity;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private JobStyle jobStyle;
	private Photo photo;
	private List<Department> departments;
}
