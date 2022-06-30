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
import com.order.management.dto.CustomersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.service.CustomerService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@MockBean
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	@Autowired
	private MockMvc mockMvc;

	private final String GET_CUSTOMERS_URL = "/api/1.0/customers";
	private final String SAVE_CUSTOMERS_URL = "/api/1.0/customers/save";

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testGetCustomers() throws Exception {
		when(customerService.findAllCustomers()).thenReturn(getCustomerList());
		MvcResult result = this.mockMvc.perform(get(GET_CUSTOMERS_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	void testSaveCustomer() throws Exception {
		when(customerService.saveNewCustomer(any(CustomersDto.class))).thenReturn(getCustomerEntity());
		String requestJson = objectMapper.writeValueAsString(getCustomersDto());
		MvcResult result = this.mockMvc
				.perform(post(SAVE_CUSTOMERS_URL).contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	List<CustomerEntity> getCustomerList() {
		List<CustomerEntity> customerEntities = new ArrayList<>();
		customerEntities.add(getCustomerEntity());
		return customerEntities;

	}

	CustomerEntity getCustomerEntity() {
		return CustomerEntity.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).id("test id").isActive(true)
				.type("test type").build();
	}

	CustomersDto getCustomersDto() {
		return CustomersDto.builder().customerId("test customerID").customerIdCity("test customerIdCity")
				.customerIdCountry("test customer Id country").customerIdEmail("test customer email")
				.customerIdPhoneNo("test customer phone no").discount(0.0).build();
	}
}
