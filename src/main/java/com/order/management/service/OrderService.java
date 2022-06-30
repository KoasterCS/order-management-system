package com.order.management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.azure.cosmos.models.PartitionKey;
import com.order.management.constants.ApplicationConstants;
import com.order.management.converter.OrderEntityConverter;
import com.order.management.dto.OrdersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.entity.OrdersEntity;
import com.order.management.repository.CustomerRepository;
import com.order.management.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final OrderEntityConverter orderEntityConverter;

	public List<OrdersEntity> findAllOrders() {
		return orderRepository.findAll(new PartitionKey(ApplicationConstants.ORDER_PARTITION_KEY)).collectList()
				.block();
	}
	
	/**
	 * @description We save the order in the database and apply discount based on the type of customer
	 * @param ordersDto
	 * @return
	 */
	public OrdersEntity saveNewOrders(OrdersDto ordersDto) {
		OrdersEntity ordersEntity = orderEntityConverter.convertOrdersEntity(ordersDto);
		CustomerEntity customerEntity = customerRepository.findByCustomerIdAndPartitionKeyAndIsActive(
				ordersEntity.getCustomerId(), ApplicationConstants.CUSTOMER_PARTITION_KEY, true).block();
		if (!ObjectUtils.isEmpty(customerEntity)) {
			customerEntity.getOrderIdList().add(ordersEntity.getOrderId());
			if (customerEntity.getOrderIdList().size() == 10) {
				customerEntity.setCustomerType(ApplicationConstants.GOLD);
				customerEntity.setDiscount(ApplicationConstants.GOLD_DISCOUNT);
			}
			if (customerEntity.getOrderIdList().size() == 20) {
				customerEntity.setCustomerType(ApplicationConstants.PLATINUM);
				customerEntity.setDiscount(ApplicationConstants.PLATINUM_DISCOUNT);
			}
			ordersEntity.setDiscount(customerEntity.getDiscount());
			customerRepository.save(customerEntity);
		} else {
			log.info("No such customer exists.");
		}
		return orderRepository.save(ordersEntity).block();
	}
}
