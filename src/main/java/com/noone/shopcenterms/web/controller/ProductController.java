package com.noone.shopcenterms.web.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noone.shopcenterms.biz.service.BizProductService;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.web.model.ViewProduct;

@RestController
@RequestMapping("/shopcenterms/product")
public class ProductController extends BaseController {
	
	@Autowired
	BizProductService bizProductService;

	
	@PostMapping("/add")
	public @ResponseBody ViewProduct add(@RequestBody ViewProduct product) {
		
		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);
		
		bizProductService.addProduct(dbProduct);
		
		return product;
	}
	
	@PostMapping("/listbycriteria")
	public @ResponseBody List<Product> listbycriteria(@RequestBody ViewProduct product) {
		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);
		return bizProductService.listProductByCriteria(dbProduct,product.getPageIndex(),product.getPageSize());
	}
	
	
}