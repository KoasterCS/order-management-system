package com.order.management.converter;

import com.order.management.constants.ApplicationConstants;
import com.order.management.dto.OrdersDto;
import com.order.management.entity.OrdersEntity;

public class OrderEntityConverter {
	public OrdersEntity convertOrdersEntity(OrdersDto ordersDto) {
		return OrdersEntity.builder().customerId(ordersDto.getCustomerId()).totalAmount(ordersDto.getTotalAmount())
				.orderId(ordersDto.getOrderId()).product(ordersDto.getProduct())
				.type(ApplicationConstants.ORDER_PARTITION_KEY).totalAmount(ordersDto.getTotalAmount()).build();
	}
}
