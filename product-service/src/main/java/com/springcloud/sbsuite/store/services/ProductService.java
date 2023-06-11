package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	public List<ProductDto> fetchProducts();
	public Optional<ProductDto> fetchProductById(Long id);

	public Optional<ProductDto> saveProduct(ProductDto dto);
	public boolean deleteProduct(ProductDto entity);
}

