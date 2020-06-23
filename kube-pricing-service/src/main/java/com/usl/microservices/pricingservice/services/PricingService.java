package com.usl.microservices.pricingservice.services;

import com.usl.microservices.pricingservice.exception.ProductNotFoundException;
import com.usl.microservices.pricingservice.model.ProductPricingModel;
import com.usl.microservices.pricingservice.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

	@Autowired
	private PricingRepository repository;

	public ProductPricingModel rerievePriceInfo(String productCode) {

		ProductPricingModel model = repository.findByProductCode(productCode);
		if (model == null) {
			throw new ProductNotFoundException("No Product found for " + productCode);
		}

		return model;
	}

	public ProductPricingModel updatePrice(String productCode, BigDecimal price) {
		ProductPricingModel model = rerievePriceInfo(productCode);
		model.setPrice(price);
		model =  repository.save(model);
		
		return model;
	}
}
