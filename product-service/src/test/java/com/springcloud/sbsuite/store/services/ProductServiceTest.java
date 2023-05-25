package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.api.NotFoundException;
import com.springcloud.sbsuite.store.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {

	@Autowired
	ProductService productService;
	@Test
	void fetchProducts() {
		List<ProductDto> products = productService.fetchProducts();
		assertNotNull(products);
		// assert products size > 0
		ProductDto product = products.get(0);
		assertNotNull(product);
		assertEquals("Product 1", product.getName());
	}

	@Test
	void testGetProductById() {
		ProductDto product = productService.fetchProductById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(product);
		assertEquals("Product 1", product.getName());
	}

	@Test
	void testGetProductByIdNotFound() {
		assertNull(productService.fetchProductById(100L).orElse(null));
	}
}