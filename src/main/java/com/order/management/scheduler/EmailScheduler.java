package com.order.management.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.order.management.entity.CustomerEntity;
import com.order.management.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class EmailScheduler {
	
	private CustomerService customerService;
	
	@Scheduled(cron = "0 * 9 * * ?")
	public void emailSchedulerOnCriteria() {
		List<CustomerEntity> customerEntities = customerService.findAllCustomers();
		customerEntities.forEach(customer->{
			if(customer.getOrderIdList().size()==9) {
				sendEmailToCustomer(customer);
			}
		});
	}
	
	private void sendEmailToCustomer(CustomerEntity customer) {
		log.info(customer.toString());
		log.info("Send email about buying one more order");
	}
}
