package com.order.management.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.order.management.entity.CustomerEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCosmosRepository<CustomerEntity, String> {
	
	public Flux<CustomerEntity> findByPartitionKeyAndIsActive(String partitionKey,Boolean isActive);
	
	public Mono<CustomerEntity> findByCustomerIdAndPartitionKeyAndIsActive(String customerId,String partitionKey,Boolean isActive);
}
