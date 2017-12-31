package com.noone.shopcenterms.web.model;

import lombok.Data;

@Data
public class ViewOrder {

	private Long id;
	
	private Integer pageIndex;
	private Integer pageSize;
	
	
	private String orderNum;
	private String status;
	private Double totalFee;
}
