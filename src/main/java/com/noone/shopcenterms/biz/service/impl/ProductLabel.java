package com.noone.shopcenterms.biz.service.impl;

import lombok.Data;

@Data
public class ProductLabel {
	
	private String sku;
	private String name;
	private String composition;
	private String produceCompany;
	private String companyMobile;
	private String companyCode;
	private String price;
	private String expiredDate;
	private String produceDate;
}
