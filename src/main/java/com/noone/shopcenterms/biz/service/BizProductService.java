package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.Product;

public interface BizProductService {
	
	Product addProduct(Product product);
	
	BizPageableResponse<List<Product>> listProductByCriteria(Product productCriteria,Integer pageIndex,Integer pageSize);
}