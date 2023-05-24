package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Product;
import com.springcloud.sbsuite.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;


	@Override
	public List<Product> fetchProducts(){
		List<Product> products = productRepository.findAll();
		return products;
	}
}
