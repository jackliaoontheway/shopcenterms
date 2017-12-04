package com.noone.shopcenterms.domain;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> , QueryDslPredicateExecutor<Product> {




}