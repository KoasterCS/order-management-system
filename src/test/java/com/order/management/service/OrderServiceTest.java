package com.order.management.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.azure.cosmos.models.PartitionKey;
import com.order.management.converter.OrderEntityConverter;
import com.order.management.dto.OrdersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.entity.OrdersEntity;
import com.order.management.repository.CustomerRepository;
import com.order.management.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	CustomerRepository customerRepository;

	@Spy
	OrderEntityConverter orderEntityConverter;

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderService orderService;

	@Test
	void testFindAllOrders() {
		when(orderRepository.findAll(any(PartitionKey.class))).thenReturn(getFluxOrdersEntity());
		List<OrdersEntity> ordersEntities = orderService.findAllOrders();
		assertNotNull(ordersEntities);
	}

	@Test
	void testSaveNewOrdersForGold() {
		when(customerRepository.findByCustomerIdAndPartitionKeyAndIsActive(anyString(), anyString(), anyBoolean()))
				.thenReturn(getMonoCustomerEntityForGold());
		when(orderRepository.save(any(OrdersEntity.class))).thenReturn(getMonoOrdersEntity());
		when(customerRepository.save(any(CustomerEntity.class))).thenReturn(getMonoCustomerEntity());
		OrdersEntity ordersEntity = orderService.saveNewOrders(getOrdersDto());
		assertNotNull(ordersEntity);
	}

	@Test
	void testSaveNewOrdersForPlatinum() {
		when(customerRepository.findByCustomerIdAndPartitionKeyAndIsActive(anyString(), anyString(), anyBoolean()))
				.thenReturn(getMonoCustomerEntityForPlatinum());
		when(orderRepository.save(any(OrdersEntity.class))).thenReturn(getMonoOrdersEntity());
		when(customerRepository.save(any(CustomerEntity.class))).thenReturn(getMonoCustomerEntity());
		OrdersEntity ordersEntity = orderService.saveNewOrders(getOrdersDto());
		assertNotNull(ordersEntity);
	}

	Mono<CustomerEntity> getMonoCustomerEntityForGold() {
		List<String> customerIdList = new ArrayList<>();
		for (int i = 0; i < 9; i++)
			customerIdList.add("order id");
		return Mono.just(CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.orderIdList(customerIdList).customerIdPhoneNo("test customer phone no").discount(0.0).id("test id")
				.isActive(true).type("test type").build());
	}

	Mono<CustomerEntity> getMonoCustomerEntityForPlatinum() {
		List<String> customerIdList = new ArrayList<>();
		for (int i = 0; i < 19; i++)
			customerIdList.add("order id");
		return Mono.just(CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.orderIdList(customerIdList).customerIdPhoneNo("test customer phone no").discount(0.0).id("test id")
				.isActive(true).type("test type").build());
	}

	Mono<CustomerEntity> getMonoCustomerEntity() {
		return Mono.just(CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).id("test id").isActive(true)
				.type("test type").build());
	}

	Flux<OrdersEntity> getFluxOrdersEntity() {
		return Flux.just(OrdersEntity.builder().id("test id").customerId("test customer id").orderId("test order id")
				.totalAmount(100.0).build());
	}

	Mono<OrdersEntity> getMonoOrdersEntity() {
		return Mono.just(OrdersEntity.builder().id("test id").customerId("test customer id").orderId("test order id")
				.totalAmount(100.0).build());
	}

	OrdersDto getOrdersDto() {
		return OrdersDto.builder().customerId("test customer id").orderId("test order id").totalAmount(100.0).build();
	}
}
