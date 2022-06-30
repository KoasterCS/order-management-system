package com.order.management.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.order.management.converter.CustomerEntityConverter;
import com.order.management.dto.CustomersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	
	@Mock
	CustomerRepository customerRepository;
	
	@Spy
	CustomerEntityConverter customerEntityConverter;
	
	@InjectMocks
	CustomerService customerService;
	
	@Test
	void testFindAllCustomers() {
		when(customerRepository.findByPartitionKeyAndIsActive(anyString(),anyBoolean())).thenReturn(getFluxCustomerEntity());
		List<CustomerEntity> customerEntities = customerService.findAllCustomers();
		assertNotNull(customerEntities);
	}

	@Test
	void testSaveNewCustomer() {
		when(customerRepository.save(any(CustomerEntity.class))).thenReturn(getMonoCustomerEntity());
		CustomerEntity customerEntity = customerService.saveNewCustomer(getCustomersDto());
		assertNotNull(customerEntity);
	}
	
	Flux<CustomerEntity> getFluxCustomerEntity() {
		return Flux.just(CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).id("test id").isActive(true)
				.type("test type").build());
	}
	
	Mono<CustomerEntity> getMonoCustomerEntity(){
		return Mono.just(CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).id("test id").isActive(true)
				.type("test type").build());
	}

	CustomersDto getCustomersDto() {
		return CustomersDto.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).build();
	}
}
