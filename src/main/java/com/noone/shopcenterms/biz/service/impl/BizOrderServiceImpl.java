package com.noone.shopcenterms.biz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import com.noone.shopcenterms.biz.service.BizOrderService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.domain.NooneOrder;
import com.noone.shopcenterms.domain.NooneOrderRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class BizOrderServiceImpl implements BizOrderService {

	@Autowired
	private NooneOrderRepository nooneOrderRepository;

	@Override
	public BizPageableResponse<List<NooneOrder>> listOrderByCriteria(NooneOrder criteria, Integer pageIndex,
			Integer pageSize) {

		BizPageableResponse<List<NooneOrder>> bizResp = new BizPageableResponse<List<NooneOrder>>();

		BooleanExpression predicate = null;

		Iterable<NooneOrder> iterable = null;
		if (pageIndex != null && pageSize != null) {
			QPageRequest pageable = new QPageRequest(pageIndex, pageSize);
			iterable = nooneOrderRepository.findAll(predicate, pageable);
		} else {
			iterable = nooneOrderRepository.findAll(predicate);
		}

		Long count = nooneOrderRepository.count(predicate);

		Iterator<NooneOrder> iterator = iterable.iterator();

		List<NooneOrder> list = new ArrayList<>();
		while (iterator.hasNext())
			list.add(iterator.next());

		bizResp.setData(list);
		bizResp.setTotalCount(count);

		return bizResp;
	}
}
