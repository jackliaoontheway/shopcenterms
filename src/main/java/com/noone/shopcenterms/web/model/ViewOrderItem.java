package com.noone.shopcenterms.web.model;

import lombok.Data;

@Data
public class ViewOrderItem {

	private Long id;
	
	
	private Long orderId;
	private String sku;
	private Double price;
	private String name;
	private Integer qty;
	private Double itemFee;

	
}
