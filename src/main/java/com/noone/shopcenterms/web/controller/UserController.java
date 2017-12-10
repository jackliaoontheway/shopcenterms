package com.noone.shopcenterms.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.web.model.ViewProduct;


@RestController
public class UserController extends BaseController {

	
	
	@PostMapping("/add")
	public @ResponseBody ViewProduct add(@RequestBody ViewProduct product) {

		Product dbProduct = new Product();
		BeanUtils.copyProperties(product, dbProduct);

//		bizProductService.addProduct(dbProduct);

		return product;
	}
	

}