package com.noone.shopcenterms.domain;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class ProductStockView extends QueryDslRepositorySupport  {

	public ProductStockView(Class<?> domainClass) {
		super(domainClass);
	}
	
	void findAll() {
		from();
	}
	
	
}
