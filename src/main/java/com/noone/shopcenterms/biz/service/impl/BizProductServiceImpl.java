package com.noone.shopcenterms.biz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.noone.shopcenterms.biz.service.BizProductService;
import com.noone.shopcenterms.domain.Product;
import com.noone.shopcenterms.domain.ProductRepository;
import com.noone.shopcenterms.domain.QProduct;
import com.querydsl.core.types.dsl.BooleanExpression;

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
	public List<Product> listProductByCriteria(Product productCriteria,Integer pageIndex,Integer pageSize) {
		
		BooleanExpression predicate = null;
		if(!StringUtils.isNullOrEmpty(productCriteria.getSku())) {
			predicate = QProduct.product.sku.eq(productCriteria.getSku());
		}
		if(!StringUtils.isNullOrEmpty(productCriteria.getName())) {
			predicate.and(QProduct.product.name.eq(productCriteria.getName()));
		}
		
		QPageRequest pageable = new QPageRequest(pageIndex,pageSize);

		Iterable<Product> iterable = productRepository.findAll(predicate,pageable);
		
		Long count = productRepository.count(predicate);
		
		Iterator<Product> iterator = iterable.iterator();

		List<Product> list = new ArrayList<>();
		while (iterator.hasNext())
			list.add(iterator.next());

		return list;
	}

}
