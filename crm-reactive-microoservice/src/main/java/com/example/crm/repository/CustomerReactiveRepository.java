package com.example.crm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

public interface CustomerReactiveRepository extends ReactiveMongoRepository<CustomerDocument, String>{

}
