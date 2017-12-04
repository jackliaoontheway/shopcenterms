package com.noone.shopcenterms.biz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noone.shopcenterms.biz.service.BizProductService;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.domain.ProductRepository;

@Component
public class BizProductServiceImpl implements BizProductService {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public Product addProduct(Product product) {
		product.setBaseField(-999L);
		return productRepository.save(product);
	}

	@Override
	public List<Product> listAllProduct() {
		
		Iterable<Product> iterable =  productRepository.findAll();
		Iterator<Product> iterator = iterable.iterator();
		
		
		List<Product> list = new ArrayList<>();
		while (iterator.hasNext())
			list.add(iterator.next());
		
		return list;
	}

}
