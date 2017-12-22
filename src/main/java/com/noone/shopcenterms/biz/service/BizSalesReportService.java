package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.domain.ProductStock;

public interface BizSalesReportService {
	
	
	BizPageableResponse<List<Product>> listProductByCriteria(Product productCriteria,Integer pageIndex,Integer pageSize);

}