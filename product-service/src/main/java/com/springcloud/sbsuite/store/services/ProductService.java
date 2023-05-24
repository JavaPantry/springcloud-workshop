package com.springcloud.sbsuite.store.services;


import com.springcloud.sbsuite.store.domain.Product;

import java.util.List;

public interface ProductService {

	public List<Product> fetchProducts();
}

