package com.example.hr.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String>{
	
	@Query("{'byear': {$gte: ?1, $lt: ?2}}")
	List<EmployeeDocument> yasAraliginaGoreGetir(int beginYear,int endYear);
	
	Collection<EmployeeDocument> findFirst10ByLastName(String lastname);
	EmployeeDocument findTopByOrderByBirthYearDesc();
	EmployeeDocument findTopByOrderBySalaryDesc();
}
