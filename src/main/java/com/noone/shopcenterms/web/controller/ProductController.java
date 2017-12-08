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
import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.common.basemodel.ServerPageableResponse;
import com.noone.shopcenterms.common.basemodel.ServerResponse;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.domain.ProductStock;
import com.noone.shopcenterms.web.model.ViewProduct;
import com.rfid.reader.RFIDfactory;

@RestController
@RequestMapping("/shopcenterms/product")
public class ProductController extends BaseController {

	@Autowired
	BizProductService bizProductService;

	@Autowired
	BizProductStockService bizProductStockService;

	@PostMapping("/add")
	public @ResponseBody ViewProduct add(@RequestBody ViewProduct product) {

		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);

		bizProductService.addProduct(dbProduct);

		return product;
	}

	@PostMapping("/readrfid")
	public @ResponseBody ServerResponse<Integer> readRFID(@RequestBody ViewProduct product) {
		
		ServerResponse<Integer> serverResponse = new ServerResponse<Integer>();

		ProductStock dbProductStock = new ProductStock();
		dbProductStock.setSku(product.getSku());
		dbProductStock.setProduceDate(product.getProduceDate());
		dbProductStock.setPrice(product.getPrice());
		
		BizResponse<Integer> bizResp = bizProductStockService.addProductStock(dbProductStock, product.getLabelCount());
		
		if (bizResp.hasError()) {
			serverResponse.setErrors(bizResp.getErrors());
		} else {
			serverResponse.setData(bizResp.getData());
		}
		
		return serverResponse;
	}

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