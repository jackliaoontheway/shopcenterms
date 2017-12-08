package com.noone.shopcenterms.biz.service;

import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.ProductStock;

public interface BizProductStockService {
	
	BizResponse<Integer> addProductStock(ProductStock product, Integer integer);
	
}