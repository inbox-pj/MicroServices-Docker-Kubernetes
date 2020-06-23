package com.usl.microservices.pricingservice.repository;

import com.usl.microservices.pricingservice.model.ProductPricingModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends CrudRepository<ProductPricingModel, Integer> {

	public ProductPricingModel findByProductCode(String productCode);
}
