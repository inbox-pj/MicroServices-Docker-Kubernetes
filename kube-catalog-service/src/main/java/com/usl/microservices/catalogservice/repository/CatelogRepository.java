package com.usl.microservices.catalogservice.repository;

import com.usl.microservices.catalogservice.model.ProductCatelogModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatelogRepository extends CrudRepository<ProductCatelogModel, Integer> {

	public ProductCatelogModel findByProductCode(String productCode);

}
