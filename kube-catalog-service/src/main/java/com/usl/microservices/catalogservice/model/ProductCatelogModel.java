package com.usl.microservices.catalogservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "PRODUCT_CATELOG")
public class ProductCatelogModel {

	@Id
	private Integer catelogId;

	private String productCode;

	private String productName;

	private String productCategory;

	private Integer reservQuanitity;

	private Integer quanitity;

	@Version
	private Integer version;

	@LastModifiedDate
	private Date whenModified;

	@Transient
	private BigDecimal price;

	public ProductCatelogModel() {

	}

	public Integer getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(Integer catelogId) {
		this.catelogId = catelogId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getReservQuanitity() {
		return reservQuanitity;
	}

	public void setReservQuanitity(Integer reservQuanitity) {
		this.reservQuanitity = reservQuanitity;
	}

	public Integer getQuanitity() {
		return quanitity;
	}

	public void setQuanitity(Integer quanitity) {
		this.quanitity = quanitity;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getWhenModified() {
		return whenModified;
	}

	public void setWhenModified(Date whenModified) {
		this.whenModified = whenModified;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
