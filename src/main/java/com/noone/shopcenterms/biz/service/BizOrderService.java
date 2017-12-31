package com.noone.shopcenterms.biz.service;

import java.util.List;

import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.NooneOrder;
import com.noone.shopcenterms.web.model.ViewOrder;

public interface BizOrderService {
	
	BizPageableResponse<List<ViewOrder>> listOrderByCriteria(NooneOrder criteria,Integer pageIndex,Integer pageSize);

}