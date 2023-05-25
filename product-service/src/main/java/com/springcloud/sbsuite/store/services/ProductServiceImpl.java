package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Product;
import com.springcloud.sbsuite.store.dto.ProductDto;
import com.springcloud.sbsuite.store.mappers.ProductMapper;
import com.springcloud.sbsuite.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;

	@Override
	public List<ProductDto> fetchProducts(){
		List<ProductDto> products = productRepository.findAll()
													.stream()
													.map(productMapper::productToProductDto)
													.collect(Collectors.toList());
		return products;
	}
}
