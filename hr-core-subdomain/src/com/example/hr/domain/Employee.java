package com.example.hr.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.example.ddd.Entity;
// Problem Space   -> Solution Space
// Core Sub-domain -> Bounded Context -> Ubiquitous Language
// Entity: i) persistent ii) identity iii) mutable
@Entity(identity="identity", aggregate = true)
public class Employee {
	private final Money MIN_WAGE = Money.valueOf(20_000, FiatCurrency.TL);
	private TcKimlikNo identity;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private JobStyle jobStyle;
	private Photo photo;
	private List<Department> departments;
	
	public static class Builder {
		private TcKimlikNo identity;
		private FullName fullName;
		private Money salary;
		private Iban iban;
		private BirthYear birthYear;
		private JobStyle jobStyle;
		private Photo photo;
		private List<Department> departments;
		
		public Builder(String identity) {
			this.identity = TcKimlikNo.of(identity);
		}
		
		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.valueOf(firstName, lastName);
			return this;
		}
		
		public Builder salary(double value,FiatCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}
		
		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}
		
		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}
		
		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}
		
		public Builder jobStyle(JobStyle jobStyle) {
			this.jobStyle = jobStyle;
			return this;
		}
		
		public Builder photo(byte[] values) {
			this.photo = Photo.valuesOf(values);
			return this;
		}
		
		public Builder photo(String base64Values) {
			this.photo = Photo.valuesOf(base64Values);
			return this;
		}
		
		public Builder departments(Department... departments) {
			this.departments = List.copyOf(Arrays.asList(departments));
			return this;
		}
		public Builder departments(List<Department> departments) {
			this.departments = List.copyOf(departments);
			return this;
		}
		
		public Employee build() {
	    	// Business Rule, Validation Rule, Invariants, Constraints, Regulations, Standards, ...
			return new Employee(this);

		}
	}


	private Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.jobStyle = builder.jobStyle;
		this.photo = builder.photo;
		this.departments = builder.departments;
		this.birthYear = builder.birthYear;
	}


	public void increaseSalary(Rate rate) {
    	var newSalary = this.salary.multiply(rate.ratio());
    	// Business Rule, Validation Rule, Invariants, Constraints, Regulations, Standards, ...
    	if (newSalary.lessThan(MIN_WAGE) && jobStyle == JobStyle.FULL_TIME)
    		throw new IllegalArgumentException("Regulation 101 is violated.");
    	this.salary = newSalary;
    }


	public Money getMIN_WAGE() {
		return MIN_WAGE;
	}


	public TcKimlikNo getIdentity() {
		return identity;
	}


	public FullName getFullName() {
		return fullName;
	}


	public Money getSalary() {
		return salary;
	}


	public Iban getIban() {
		return iban;
	}


	public BirthYear getBirthYear() {
		return birthYear;
	}


	public JobStyle getJobStyle() {
		return jobStyle;
	}


	public Photo getPhoto() {
		return photo;
	}


	public List<Department> getDepartments() {
		return departments;
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
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}


	@Override
	public String toString() {
		return "Employee [ identity=" + identity + ", fullName=" + fullName + ", salary="
				+ salary + ", iban=" + iban + ", birthYear=" + birthYear + ", jobStyle=" + jobStyle + ", departments="
				+ departments + "]";
	}
	
	
}
