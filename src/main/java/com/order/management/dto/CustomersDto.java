package com.order.management.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomersDto implements Serializable{

	private static final long serialVersionUID = 976382215507133622L;
	private String customerId;
	private String customerIdFirstname;
	private String customerIdLastname;
	private String customerIdEmail;
	private String customerIdPhoneNo;
	private String customerIdCity;
	private String customerIdProvince;
	private String customerIdCountry;
	private String customerType;
	private Double discount;
}
