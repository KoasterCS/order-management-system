package com.order.management.converter;

import com.order.management.constants.ApplicationConstants;
import com.order.management.dto.CustomersDto;
import com.order.management.entity.CustomerEntity;

public class CustomerEntityConverter {
	public CustomerEntity convertToEntity(CustomersDto customersDto) {
		return CustomerEntity.builder().customerId(customersDto.getCustomerId())
				.customerIdCity(customersDto.getCustomerIdCity()).customerIdCountry(customersDto.getCustomerIdCountry())
				.customerIdEmail(customersDto.getCustomerIdEmail())
				.customerIdFirstname(customersDto.getCustomerIdFirstname())
				.customerIdLastname(customersDto.getCustomerIdLastname())
				.customerIdPhoneNo(customersDto.getCustomerIdPhoneNo())
				.type(ApplicationConstants.CUSTOMER_PARTITION_KEY).isActive(true).build();
	}
}
