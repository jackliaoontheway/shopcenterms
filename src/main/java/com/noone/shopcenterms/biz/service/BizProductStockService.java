package com.noone.shopcenterms.biz.service;

import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.domain.ProductStock;

public interface BizProductStockService {
	
	BizResponse<Boolean> addProductStock(ProductStock product, Integer integer);

}