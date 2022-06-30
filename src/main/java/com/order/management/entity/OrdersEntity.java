package com.order.management.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.order.management.model.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Container(containerName = "orders", autoCreateContainer = false)
public class OrdersEntity {
    @Id
    private String id;
    @PartitionKey
    private String type;
    private String orderId;
    private String customerId;
    private Double totalAmount;
    private Double discount;
    List<ProductModel> product;
}
