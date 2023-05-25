package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	public List<ProductDto> fetchProducts();
	public Optional<ProductDto> fetchProductById(Long id);
}

