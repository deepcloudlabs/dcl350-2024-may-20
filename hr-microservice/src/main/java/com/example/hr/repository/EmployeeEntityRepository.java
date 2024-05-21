package com.example.hr.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.domain.Employee;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	List<Employee> getAllByBirthYearBetween(int beginYear,int endYear);
	
	// JPQL
	@Query(value = "select e from EmployeeEntity e where e.birthYear between :beginYear and :endYear", nativeQuery = false)
	List<Employee> yasAraliginaGoreGetir(int beginYear,int endYear);
	
	// Native SQL
	@Query(value = "select e from employees e where e.byear between :beginYear and :endYear", nativeQuery = true)
	List<Employee> bulGetir(int beginYear,int endYear);
	
	Collection<Employee> findFirst10ByLastName(String lastname);
	Employee findTopByOrderByBirthYearDesc();
	Employee findTopByOrderBySalaryDesc();
}
