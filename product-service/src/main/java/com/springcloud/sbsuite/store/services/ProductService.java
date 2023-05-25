package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.dto.ProductDto;

import java.util.List;

public interface ProductService {

	public List<ProductDto> fetchProducts();
}

