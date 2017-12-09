package com.noone.shopcenterms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("/shopcenterms/product")
public class ProductController extends BaseController {

	@Autowired
	BizProductService bizProductService;

	@Autowired
	BizProductStockService bizProductStockService;
	
	private String labelpath = "C:/labels/";

	@PostMapping("/add")
	public @ResponseBody ViewProduct add(@RequestBody ViewProduct product) {

		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);

		bizProductService.addProduct(dbProduct);

		return product;
	}

	@PostMapping("/readrfid")
	public @ResponseBody ServerResponse<String> readRFID(@RequestBody ViewProduct viewProduct) {

		ServerResponse<String> serverResponse = new ServerResponse<String>();

		ProductStock dbProductStock = new ProductStock();
		dbProductStock.setSku(viewProduct.getSku());
		dbProductStock.setProduceDate(viewProduct.getProduceDate());
		dbProductStock.setPrice(viewProduct.getPrice());

		BizResponse<Boolean> bizResp = bizProductStockService.addProductStock(dbProductStock,
				viewProduct.getLabelCount());

		if (bizResp.hasError()) {
			serverResponse.setErrors(bizResp.getErrors());
		} else {
			Product dbProduct = new Product();
			BeanUtils.copyProperties(viewProduct, dbProduct);
			BizResponse<String> bizCreateLableResp = bizProductService.createProductlabel(dbProduct,
					viewProduct.getPrice(), viewProduct.getProduceDate(), viewProduct.getLabelCount());
			serverResponse.setData(bizCreateLableResp.getData());
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

	@PostMapping("/listproductstockbycriteria")
	public @ResponseBody ServerPageableResponse<List<Product>> listproductstockbycriteria(@RequestBody ViewProduct product) {
		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);
		
		ServerPageableResponse<List<Product>> serverResp = new ServerPageableResponse<List<Product>>();
		
		BizPageableResponse<List<Product>> bizResp = bizProductService.listProductByCriteria(dbProduct,
				product.getPageIndex(), product.getPageSize());
		
		serverResp.setData(bizResp.getData());
		serverResp.setTotalCount(bizResp.getTotalCount());
		
		return serverResp;
		
	}
	
	@GetMapping("/downloadlabels")
	public @ResponseBody Void downloadlabels(HttpServletResponse response , String downloadId) {
		sendPdfFileToPage(labelpath,downloadId,response);
		return null;
	}
	
	

}