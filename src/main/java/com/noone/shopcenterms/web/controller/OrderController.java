package com.noone.shopcenterms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noone.shopcenterms.biz.service.BizOrderService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.ServerPageableResponse;
import com.noone.shopcenterms.web.model.ViewOrder;

@RestController
@RequestMapping("/shopcenterms/order")
public class OrderController extends BaseController {

	@Autowired
	BizOrderService bizOrderService;


	@PostMapping("/listbycriteria")
	public @ResponseBody ServerPageableResponse<List<ViewOrder>> listbycriteria(@RequestBody ViewOrder viewOrder) {

		ServerPageableResponse<List<ViewOrder>> serverResp = new ServerPageableResponse<List<ViewOrder>>();

		BizPageableResponse<List<ViewOrder>> bizResp = bizOrderService.listOrderByCriteria(null,
				viewOrder.getPageIndex(), viewOrder.getPageSize());

		serverResp.setData(bizResp.getData());
		serverResp.setTotalCount(bizResp.getTotalCount());
 
		return serverResp;

	}

}