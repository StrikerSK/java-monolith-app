package com.application.customer.service;

import com.application.customer.entity.ValidationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationMockedService implements IValidationService {

	Logger LOG = LoggerFactory.getLogger(ValidationMockedService.class);

	public void createCustomer(ValidationEntity validationEntity) {
		LOG.info("createCustomer(): {}", validationEntity.toString());
	}
}
