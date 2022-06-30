package com.order.management.repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.order.management.entity.OrdersEntity;

public interface OrderRepository extends ReactiveCosmosRepository<OrdersEntity, String> {

}
