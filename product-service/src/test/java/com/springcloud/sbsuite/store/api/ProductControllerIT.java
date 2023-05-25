package com.springcloud.sbsuite.store.api;

import com.springcloud.sbsuite.store.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductControllerIT {
	@Autowired
	ProductController productController;

	@Test
	void testListProducts() {

		List<ProductDto> products = productController.getProducts();
		assertNotNull(products);
		// assert products size > 0
		ProductDto product = products.get(0);
		assertNotNull(product);
		assertEquals("Product 1", product.getName());
	}
}