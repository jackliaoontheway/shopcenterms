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
import com.noone.shopcenterms.domain.OrderItem;
import com.noone.shopcenterms.domain.OrderItemRepository;
import com.noone.shopcenterms.domain.QOrderItem;
import com.noone.shopcenterms.web.model.ViewOrder;
import com.noone.shopcenterms.web.model.ViewOrderItem;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class BizOrderServiceImpl implements BizOrderService {

	@Autowired
	private NooneOrderRepository nooneOrderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public BizPageableResponse<List<ViewOrder>> listOrderByCriteria(NooneOrder criteria, Integer pageIndex,
			Integer pageSize) {

		BizPageableResponse<List<ViewOrder>> bizResp = new BizPageableResponse<List<ViewOrder>>();

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
		
		List<ViewOrder> viewList = convert(list);

		bizResp.setData(viewList);
		bizResp.setTotalCount(count);

		return bizResp;
	}

	private List<ViewOrder> convert(List<NooneOrder> list) {

		List<ViewOrder> result = new ArrayList<>();
		
		for (NooneOrder order : list) {
			ViewOrder vieworder = new ViewOrder();
			vieworder.setOrderNum(order.getOrderNum());
			vieworder.setStatus(order.getStatus());
			vieworder.setTotalFee(order.getTotalFee());
			
			List<ViewOrderItem> orderItemlist = new ArrayList<>();
			Iterable<OrderItem> iterable = orderItemRepository.findAll(QOrderItem.orderItem.orderId.eq(order.getId()));
			Iterator<OrderItem> iterator = iterable.iterator();
			while (iterator.hasNext()) {
				OrderItem orderitem = iterator.next();
				ViewOrderItem vieworderitem = new ViewOrderItem();
				vieworderitem.setItemFee(orderitem.getItemFee());
				vieworderitem.setName(orderitem.getName());
				vieworderitem.setPrice(orderitem.getPrice());
				vieworderitem.setQty(orderitem.getQty());
				vieworderitem.setSku(orderitem.getSku());
				orderItemlist.add(vieworderitem);
			}
				
			result.add(vieworder);
		}
		
		return result;
	}
}
