package com.example.hr.dto.response;

import java.util.List;

import com.example.hexagon.DTO;
import com.example.hexagon.DTOType;
import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

@DTO(DTOType.RESPONSE)
public class FireEmployeeResponse {
	private String identity;
	private String firstName;
	private String lastName;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private int birthYear;
	private JobStyle jobStyle;
	private String photo;
	private List<Department> departments;

	public final String getIdentity() {
		return identity;
	}

	public final void setIdentity(String identity) {
		this.identity = identity;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final double getSalary() {
		return salary;
	}

	public final void setSalary(double salary) {
		this.salary = salary;
	}

	public final FiatCurrency getCurrency() {
		return currency;
	}

	public final void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public final String getIban() {
		return iban;
	}

	public final void setIban(String iban) {
		this.iban = iban;
	}

	public final int getBirthYear() {
		return birthYear;
	}

	public final void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public final JobStyle getJobStyle() {
		return jobStyle;
	}

	public final void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public final String getPhoto() {
		return photo;
	}

	public final void setPhoto(String photo) {
		this.photo = photo;
	}

	public final List<Department> getDepartments() {
		return departments;
	}

	public final void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "FireEmployeeResponse [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", currency=" + currency + ", iban=" + iban + ", birthYear=" + birthYear
				+ ", jobStyle=" + jobStyle + ", departments=" + departments + "]";
	}

}
