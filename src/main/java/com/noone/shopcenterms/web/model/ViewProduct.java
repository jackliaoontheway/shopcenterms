package com.noone.shopcenterms.web.model;

import lombok.Data;

@Data
public class ViewProduct {

	private Long id; 
	private String sku;
	private String name;
	private String composition;
	private String produceCompany;
	private String companyMobile;
	private String companyCode;
	private String price;
	private String expiredDate;
	private String produceAddress;
	
	
	private String produceDate;
	private Integer labelCount;
	
	private Integer pageIndex;
	private Integer pageSize;
	
	
}
