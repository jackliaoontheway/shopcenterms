package com.noone.shopcenterms.biz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.noone.shopcenterms.biz.service.BizProductStockService;
import com.noone.shopcenterms.common.basemodel.BizPageableResponse;
import com.noone.shopcenterms.common.basemodel.BizResponse;
import com.noone.shopcenterms.domain.ProductStock;
import com.noone.shopcenterms.domain.ProductStockRepository;
import com.noone.shopcenterms.domain.QProductStock;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rfid.reader.RFIDfactory;

@Component
public class BizProductStockServiceImpl implements BizProductStockService {

	@Autowired
	ProductStockRepository productStockRepository;

	@Override
	public BizResponse<Boolean> addProductStock(ProductStock product, Integer labelCount) {

		BizResponse<Boolean> bizResp = new BizResponse<Boolean>();

		RFIDfactory factory = RFIDfactory.getInstance();
		List<String> list = factory.readAllRFID("COM4");

		if (list == null || labelCount == null) {
			bizResp.addError("感应失败,请检查机器.");
			return bizResp;
		} else if (list.size() != labelCount) {
			bizResp.addError("数量不一致,感应到的RFID个数: " + (list == null ? 0 : list.size()) + "打印的个数: " + labelCount);
			return bizResp;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String rfid = list.get(i);
			if (checkRFIDisExisted(rfid)) {
				sb.append(rfid + ",");
			}
		}
		if (sb.length() > 0) {
			bizResp.addError("RFID已经存在被读取过, RFID:" + sb);
			return bizResp;
		}

		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			ProductStock dbProduct = new ProductStock();
			BeanUtils.copyProperties(product, dbProduct);
			dbProduct.setId(null);
			dbProduct.setStatus("NEW");
			dbProduct.setRfid(list.get(i));
			dbProduct.setBaseField(-999L);
			ProductStock saveResult = productStockRepository.save(dbProduct);

			if (saveResult != null && saveResult.getId() != null) {
				result++;
			}
		}

		bizResp.setData(result == labelCount);
		return bizResp;
	}

	private Boolean checkRFIDisExisted(String rfid) {
		return false;
//		Predicate predicate = QProductStock.productStock.rfid.eq(rfid);
//		Iterable<ProductStock> productStock = productStockRepository.findAll(predicate);
//		return (productStock != null && productStock.iterator().hasNext());
	}

	@Override
	public BizPageableResponse<List<ProductStock>> listProductStockByCriteria(ProductStock criteria, Integer pageIndex,
			Integer pageSize) {

		BizPageableResponse<List<ProductStock>> bizResp = new BizPageableResponse<List<ProductStock>>();

		BooleanExpression predicate = null;
		if (!StringUtils.isNullOrEmpty(criteria.getSku())) {
			predicate = QProductStock.productStock.sku.like("%" + criteria.getSku() + "%");
		}
		if (!StringUtils.isNullOrEmpty(criteria.getName())) {
			if (predicate == null) {
				predicate = QProductStock.productStock.name.like("%" + criteria.getName() + "%");
			} else {
				predicate.and(QProductStock.productStock.name.like("%" + criteria.getName() + "%"));
			}
		}

		Iterable<ProductStock> iterable = null;
		if (pageIndex != null && pageSize != null) {
			QPageRequest pageable = new QPageRequest(pageIndex, pageSize);
			iterable = productStockRepository.findAll(predicate, pageable);
		} else {
			iterable = productStockRepository.findAll(predicate);
		}

		Long count = productStockRepository.count(predicate);

		Iterator<ProductStock> iterator = iterable.iterator();

		List<ProductStock> list = new ArrayList<>();
		while (iterator.hasNext())
			list.add(iterator.next());

		bizResp.setData(list);
		bizResp.setTotalCount(count);

		return bizResp;
	}

}
