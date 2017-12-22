package com.noone.shopcenterms.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.noone.shopcenterms.biz.service.BizOrderService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.NooneOrder;

@Component
public class BizOrderServiceImpl implements BizOrderService {

	@Override
	public BizPageableResponse<List<NooneOrder>> listOrderByCriteria(NooneOrder criteria, Integer pageIndex,
			Integer pageSize) {

		
		
		return null;
	}
}
