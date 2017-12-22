package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.NooneOrder;

public interface BizOrderService {
	
	BizPageableResponse<List<NooneOrder>> listOrderByCriteria(NooneOrder criteria,Integer pageIndex,Integer pageSize);

}