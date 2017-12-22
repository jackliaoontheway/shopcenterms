package com.noone.shopcenterms.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.noone.shopcenterms.biz.service.BizSalesReportService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.Product;

@Component
public class BizSalesReportServiceImpl implements BizSalesReportService {

	@Override
	public BizPageableResponse<List<Product>> listProductByCriteria(Product productCriteria, Integer pageIndex,
			Integer pageSize) {
		return null;
	}

}
