package com.noone.shopcenterms.web.model;

import lombok.Data;

@Data
public class ViewProduct {

	private Long id; 
	private String name;
	private String composition;
	private String produceCompany;
	private String companyMobile;
	private String companyCode;
	private String price;
	private String expiredDate;
	
	
}
