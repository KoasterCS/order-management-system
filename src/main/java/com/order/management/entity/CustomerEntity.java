package com.order.management.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Container(containerName = "customer", autoCreateContainer = false)
public class CustomerEntity {
    
	@Id
	private String id;
	@PartitionKey
	private String type;
	private String customerId;
	private String customerIdFirstname;
	private String customerIdLastname;
	private String customerIdEmail;
	private String customerIdPhoneNo;
	private String customerIdCity;
	private String customerIdCountry;
	private String customerType;
	private Double discount;
	private Boolean isActive;
	private List<String> orderIdList;

}
