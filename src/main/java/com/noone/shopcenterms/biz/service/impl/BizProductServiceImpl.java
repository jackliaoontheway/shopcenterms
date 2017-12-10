package com.noone.shopcenterms.biz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.noone.shopcenterms.biz.service.BizProductService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.BizResponse;
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
	public BizPageableResponse<List<Product>> listProductByCriteria(Product productCriteria, Integer pageIndex,
			Integer pageSize) {

		BizPageableResponse<List<Product>> bizResp = new BizPageableResponse<List<Product>>();

		BooleanExpression predicate = null;
		if (!StringUtils.isNullOrEmpty(productCriteria.getSku())) {
			predicate = QProduct.product.sku.like("%" + productCriteria.getSku() + "%");
		}
		if (!StringUtils.isNullOrEmpty(productCriteria.getName())) {
			if (predicate == null) {
				predicate = QProduct.product.name.like("%" + productCriteria.getName() + "%");
			} else {
				predicate.and(QProduct.product.name.like("%" + productCriteria.getName() + "%"));
			}
		}

		Iterable<Product> iterable = null;
		if (pageIndex != null && pageSize != null) {
			QPageRequest pageable = new QPageRequest(pageIndex, pageSize);
			iterable = productRepository.findAll(predicate, pageable);
		} else {
			iterable = productRepository.findAll(predicate);
		}

		Long count = productRepository.count(predicate);

		Iterator<Product> iterator = iterable.iterator();

		List<Product> list = new ArrayList<>();
		while (iterator.hasNext())
			list.add(iterator.next());

		bizResp.setData(list);
		bizResp.setTotalCount(count);

		return bizResp;
	}

	@Override
	public BizResponse<String> createProductlabel(Product product, String price, String produceDate, int count) {
		BizResponse<String> bizResp = new BizResponse<String>();

		ProductLabel productLabel = new ProductLabel();
		BeanUtils.copyProperties(product, productLabel);
		productLabel.setPrice(price);
		productLabel.setProduceDate(produceDate);
		String path = ProductLabelFactory.getInstance().createProductLabel(productLabel, count, "C:/labels");
		bizResp.setData(path);
		return bizResp;
	}

	@Override
	public BizResponse<Boolean> deleteProduct(Product dbProduct) {
		BizResponse<Boolean> bizResp = new BizResponse<Boolean>();
		productRepository.delete(dbProduct.getId());
		bizResp.setData(true);
		return bizResp;
	}

}
