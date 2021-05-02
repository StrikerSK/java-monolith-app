package com.application.customer.service;

import com.application.customer.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerMockedService implements ICustomerService {

	Logger LOG = LoggerFactory.getLogger(CustomerMockedService.class);

	public void createCustomer(Customer customer) {
		LOG.info("createCustomer(): {}", customer.toString());
	}
}
