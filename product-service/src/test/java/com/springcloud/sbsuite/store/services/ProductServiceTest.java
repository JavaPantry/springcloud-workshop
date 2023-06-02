package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.api.NotFoundException;
import com.springcloud.sbsuite.store.dto.ProductDto;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

	@Rollback
	@Transactional
	@Test
	void saveNewProduct() {
		ProductDto product = new ProductDto().builder().name("test product").description("test product description").build();
		ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
		assertNotNull(newProduct);
		assertEquals("test product", newProduct.getName());
		assertEquals("test product description", newProduct.getDescription());
		product = productService.fetchProductById(newProduct.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(product);
		assertEquals("test product", product.getName());
		assertEquals("test product description", product.getDescription());
	}

	@Rollback
	@Transactional
	@Test
	void saveNewInvalidProduct() {
		assertThrows(ConstraintViolationException.class, () -> {
			ProductDto product = new ProductDto().builder().build();
			ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
			assertNotNull(newProduct);
		});
	}

	@Rollback
	@Transactional
	@Test
	void saveInvalidProductWithTooShortName() {
		assertThrows(ConstraintViolationException.class, () -> {
			ProductDto product = new ProductDto().builder().name("E").build();
			ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
			assertNotNull(newProduct);
		});
	}
	@Rollback
	@Transactional
	@Test
	void saveProduct() {
		ProductDto product = productService.fetchProductById(1L).orElseThrow(NotFoundException::new);
		product.setName("update product");
		product.setDescription("update product description");
		ProductDto updatedProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
		assertNotNull(updatedProduct);
		assertEquals("update product description", updatedProduct.getDescription());
		product = productService.fetchProductById(1L).orElseThrow(NotFoundException::new);
		assertEquals("update product description", product.getDescription());
	}

	@Rollback
	@Transactional
	@Test
	void deleteProduct() {
		ProductDto product = new ProductDto().builder().name("test product").description("test product descr").build();
		ProductDto newProduct = productService.saveProduct(product).orElseThrow(NotFoundException::new);
		assertNotNull(newProduct);
		assertEquals("test product", newProduct.getName());
		assertEquals("test product descr", newProduct.getDescription());
		product = productService.fetchProductById(newProduct.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(product);
		assertEquals("test product", product.getName());
		assertEquals("test product descr", product.getDescription());
		productService.deleteProduct(product);
		product = productService.fetchProductById(product.getId()).orElse(null);
		assertNull(product);
	}
}