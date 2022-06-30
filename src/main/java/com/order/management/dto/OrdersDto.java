package com.order.management.dto;

import java.io.Serializable;
import java.util.List;

import com.order.management.model.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto implements Serializable{

	private static final long serialVersionUID = 8603175241387891587L;
	private String orderId;
	private String customerId;
	private Double totalAmount;
	private Double discount;
	private transient List<ProductModel> product;
}
