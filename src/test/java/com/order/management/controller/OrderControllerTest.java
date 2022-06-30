package com.order.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.management.dto.OrdersDto;
import com.order.management.entity.OrdersEntity;
import com.order.management.service.OrderService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

	@MockBean
	OrderService orderService;

	@InjectMocks
	OrderController orderController;

	@Autowired
	private MockMvc mockMvc;

	private final String GET_ORDERS_URL = "/api/1.0/orders";
	private final String SAVE_ORDERS_URL = "/api/1.0/orders/save";

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testGetOrders() throws Exception {
		when(orderService.findAllOrders()).thenReturn(getOrdersEntityList());
		MvcResult result = this.mockMvc.perform(get(GET_ORDERS_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testSaveOrder() throws Exception {
		when(orderService.saveNewOrders(any(OrdersDto.class))).thenReturn(getOrdersEntity());
		String requestJson = objectMapper.writeValueAsString(getOrdersDto());
		MvcResult result = this.mockMvc
				.perform(post(SAVE_ORDERS_URL).contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	List<OrdersEntity> getOrdersEntityList(){
		List<OrdersEntity> ordersEntities = new ArrayList<>();
		ordersEntities.add(getOrdersEntity());
		return ordersEntities;
	}
	
	OrdersEntity getOrdersEntity() {
		return OrdersEntity.builder().id("test id").customerId("test customer id").orderId("test order id")
				.totalAmount(100.0).build();
	}

	OrdersDto getOrdersDto() {
		return OrdersDto.builder().customerId("test customer id").orderId("test order id").totalAmount(100.0).build();
	}
}
