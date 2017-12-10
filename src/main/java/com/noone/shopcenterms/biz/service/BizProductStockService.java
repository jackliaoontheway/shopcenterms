package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.ProductStock;

public interface BizProductStockService {
	
	BizResponse<Boolean> addProductStock(ProductStock product, Integer integer);
	
	BizPageableResponse<List<ProductStock>> listProductStockByCriteria(ProductStock criteria,Integer pageIndex,Integer pageSize);

}