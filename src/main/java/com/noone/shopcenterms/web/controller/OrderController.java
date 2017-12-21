package com.noone.shopcenterms.web.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noone.shopcenterms.biz.service.BizProductService;
import com.noone.shopcenterms.biz.service.BizProductStockService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.ServerPageableResponse;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.web.model.ViewProduct;

@RestController
@RequestMapping("/shopcenterms/order")
public class OrderController extends BaseController {

	@Autowired
	BizProductService bizProductService;

	@Autowired
	BizProductStockService bizProductStockService;


	@PostMapping("/listbycriteria")
	public @ResponseBody ServerPageableResponse<List<Product>> listbycriteria(@RequestBody ViewProduct product) {
		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);

		ServerPageableResponse<List<Product>> serverResp = new ServerPageableResponse<List<Product>>();

		BizPageableResponse<List<Product>> bizResp = bizProductService.listProductByCriteria(dbProduct,
				product.getPageIndex(), product.getPageSize());

		serverResp.setData(bizResp.getData());
		serverResp.setTotalCount(bizResp.getTotalCount());

		return serverResp;

	}

}