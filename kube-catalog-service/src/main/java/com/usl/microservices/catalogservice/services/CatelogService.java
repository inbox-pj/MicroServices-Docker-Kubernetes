package com.usl.microservices.catalogservice.services;

import com.usl.microservices.catalogservice.client.PricingServiceFeignClient;
import com.usl.microservices.catalogservice.client.model.ProductPricingModel;
import com.usl.microservices.catalogservice.model.AgreegateCatelogModel;
import com.usl.microservices.catalogservice.model.ProductCatelogModel;
import com.usl.microservices.catalogservice.repository.CatelogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatelogService {

    @Autowired
    private CatelogRepository repository;

    @Autowired
    private PricingServiceFeignClient pricingServiceClient;

    public ProductCatelogModel lookupProduct(String productCode) {
        ProductCatelogModel model = findByProductCode(productCode);
        if (model != null) {
            ProductPricingModel pricingModel = pricingServiceClient.checkPrice(productCode).getBody();
            if (pricingModel != null) {
                model.setPrice(pricingModel.getPrice());
            }
        }

        return model;
    }

    public AgreegateCatelogModel searchInventory() {
        Iterable<ProductCatelogModel> iterable = repository.findAll();

        AgreegateCatelogModel agreegateModel = new AgreegateCatelogModel();
        for (ProductCatelogModel productCatelogModel : iterable) {

            ProductPricingModel pricingModel = pricingServiceClient
                    .checkPrice(productCatelogModel.getProductCode()).getBody();
            if (pricingModel != null) {
                productCatelogModel.setPrice(pricingModel.getPrice());
            }

            agreegateModel.addProductCatelog(productCatelogModel);

        }

        return agreegateModel;
    }

    public String reserveProductQuantity(String productCode, Integer quantity) {
        ProductCatelogModel model = lookupProduct(productCode);

        if (model != null) {
            if (model.getQuanitity() >= quantity) {
                model.setQuanitity(model.getQuanitity() - quantity);
                model.setReservQuanitity(model.getReservQuanitity() + quantity);
                repository.save(model);
                return "RESERVED";
            } else {
                return "INSUFFICIENT_QTY";
            }
        }
        return "PRODUCT_NOT_FOUND";
    }

    public String consumeInventory(String productCode, Integer quantity) {
        ProductCatelogModel model = findByProductCode(productCode);

        if (model != null) {
            if (model.getReservQuanitity() >= quantity) {
                model.setReservQuanitity(model.getReservQuanitity() - quantity);
                repository.save(model);
                return "CONSUEMD";
            } else {
                return "INSUFFICIENT_QTY";
            }
        }
        return "PRODUCT_NOT_FOUND";
    }

    public String clearReserve(String productCode, Integer quantity) {
        ProductCatelogModel model = findByProductCode(productCode);

        if (model != null) {
            if (model.getReservQuanitity() >= quantity) {
                model.setReservQuanitity(model.getReservQuanitity() - quantity);
                model.setQuanitity(model.getQuanitity() + quantity);
                repository.save(model);
                return "CLEARED";
            } else {
                return "INSUFFICIENT_QTY";
            }
        }
        return "PRODUCT_NOT_FOUND";
    }

    public String updateInventory(String productCode, Integer quantity) {
        ProductCatelogModel model = findByProductCode(productCode);

        if (model != null) {
            model.setQuanitity(model.getQuanitity() + quantity);
            repository.save(model);
            return "SUCCESS";
        }
        return "PRODUCT_NOT_FOUND";
    }

    private ProductCatelogModel findByProductCode(String productCode) {
        return repository.findByProductCode(productCode);
    }
}
