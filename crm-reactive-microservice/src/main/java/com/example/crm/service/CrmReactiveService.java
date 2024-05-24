package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.events.CustomerAcquiredEvent;
import com.example.crm.events.CustomerReleasedEvent;
import com.example.crm.repository.CustomerReactiveRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerReactiveRepository customerReactiveRepository;
	private final ReactiveKafkaProducerTemplate<String,String> reactiveKafkaProducerTemplate;
	private final ObjectMapper objectMapper;
	
	public CrmReactiveService(CustomerReactiveRepository customerReactiveRepository, ObjectMapper objectMapper, ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate) {
		this.customerReactiveRepository = customerReactiveRepository;
		this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
		this.objectMapper = objectMapper;
	}

	public Mono<CustomerDocument> findCustomerById(String identity) {
		return customerReactiveRepository.findById(identity);
	}

	public Flux<CustomerDocument> findAllCustomers(int pageNo, int pageSize) {
		return customerReactiveRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> acquireCustomer(CustomerDocument customer) {
		return customerReactiveRepository.insert(customer)
				.doOnSuccess(insertedCustomer -> {
					var event = new CustomerAcquiredEvent(customer.getIdentity());
					try {
						var eventAsJson = objectMapper.writeValueAsString(event);
						reactiveKafkaProducerTemplate.send("crm-events", eventAsJson)
						                             .subscribe();						
					}catch (Exception e) {
						System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
					}
				});
	}

	public Mono<CustomerDocument> releaseCustomer(String identity) {
		return customerReactiveRepository.findById(identity)
				                         .doOnSuccess(customer -> {
				                        	 customerReactiveRepository.delete(customer).doOnSuccess((consumer) -> {
				             					var event = new CustomerReleasedEvent(customer.getIdentity());
				            					try {
				            						var eventAsJson = objectMapper.writeValueAsString(event);
				            						reactiveKafkaProducerTemplate.send("crm-events", eventAsJson)
				            						                              .subscribe();						
				            					}catch (Exception e) {
				            						System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
				            					}
				                        	 });
				                         });
	}

}
