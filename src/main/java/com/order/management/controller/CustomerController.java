package com.order.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.management.dto.CustomersDto;
import com.order.management.entity.CustomerEntity;
import com.order.management.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping("/api/1.0/customers")
	@ApiOperation(value = "Get All Customers", response = CustomerEntity.class)
	public ResponseEntity<List<CustomerEntity>> getCustomers() {
		return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
	}

	@PostMapping("/api/1.0/customers/save")
	@ApiOperation(value = "Save new customers", response = CustomerEntity.class)
	public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomersDto customer) {
		return new ResponseEntity<>(customerService.saveNewCustomer(customer), HttpStatus.OK);
	}

}
