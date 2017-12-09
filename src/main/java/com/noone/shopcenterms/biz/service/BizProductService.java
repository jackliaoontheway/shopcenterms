package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.Product;

public interface BizProductService {
	
	Product addProduct(Product product);
	
	BizResponse<String> createProductlabel(Product product,String price,String produceDate,int count);
	
	BizPageableResponse<List<Product>> listProductByCriteria(Product productCriteria,Integer pageIndex,Integer pageSize);

	BizResponse<Boolean> deleteProduct(Product dbProduct);
}