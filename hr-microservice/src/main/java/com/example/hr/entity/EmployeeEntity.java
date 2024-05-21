package com.example.hr.entity;

import java.util.List;
import java.util.Objects;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@Column(name = "id")
	private String identity;
	@Column(name = "fname")
	private String firstName;
	@Column(name = "lname")
	private String lastName;
	@Column(name = "maas")
	private double salary;
	@Enumerated(EnumType.STRING)
	private FiatCurrency currency;
	private String iban;
	@Column(name = "byear")
	private int birthYear;
	@Enumerated(EnumType.ORDINAL)
	private JobStyle jobStyle;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@ElementCollection
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

	public final byte[] getPhoto() {
		return photo;
	}

	public final void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public final List<Department> getDepartments() {
		return departments;
	}

	public final void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", currency=" + currency + ", iban=" + iban + ", birthYear=" + birthYear
				+ ", jobStyle=" + jobStyle + ", departments=" + departments + "]";
	}

}
