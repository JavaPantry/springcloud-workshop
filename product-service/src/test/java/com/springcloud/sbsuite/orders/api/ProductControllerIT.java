package com.springcloud.sbsuite.orders.api;

import com.springcloud.sbsuite.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void testGetProductById() {
		ProductDto product = productController.getProductById(1L);
		assertNotNull(product);
		assertEquals("Product 1", product.getName());
	}

	@Test
	void testGetProductByIdNotFound() {
		assertThrows(NotFoundException.class, () -> {
			productController.getProductById(100L);
		});
	}

	@Test
	void testPostProduct() {
		ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();
		//ProductDto product = new ProductDto().builder().description("").build();
		ProductDto newProduct = productController.createProduct(product).getBody();
		assertNotNull(newProduct);
		assertEquals("test product", newProduct.getName());
		assertEquals("test product description", newProduct.getDescription());
		product = productController.getProductById(newProduct.getId());
		assertNotNull(product);
		assertEquals("test product", product.getName());
		assertEquals("test product description", product.getDescription());

	}
}