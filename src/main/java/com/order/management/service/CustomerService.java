package com.order.management.service;

import java.util.List;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.order.management.constants.ApplicationConstants;
import com.order.management.converter.CustomerEntityConverter;
import com.order.management.dto.CustomersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableAsync
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final CustomerEntityConverter customerEntityConverter;
	
	public List<CustomerEntity> findAllCustomers() {
		return customerRepository.findByPartitionKeyAndIsActive(ApplicationConstants.CUSTOMER_PARTITION_KEY, true).collectList().block();
	}
	
	/**
	 * @description For a new customer will be always regular and dont have a discount
	 * @param customersDto
	 * @return
	 */
	public CustomerEntity saveNewCustomer(CustomersDto customersDto) {
		CustomerEntity customerEntity = customerEntityConverter.convertToEntity(customersDto);
		customerEntity.setDiscount(0.0);
		customerEntity.setCustomerType(ApplicationConstants.REGULAR);
		return customerRepository.save(customerEntity).block();
	}
}
