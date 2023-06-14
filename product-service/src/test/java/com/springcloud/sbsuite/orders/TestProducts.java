package com.springcloud.sbsuite.orders;


import com.springcloud.sbsuite.orders.domain.Product;
import com.springcloud.sbsuite.orders.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @ComponentScan(basePackages = {"com.sbsuite.sbsuitedata.bootstrap"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestProducts {

	@Autowired
	ProductRepository productRepository;

	@Test
	void testFetchProducts() {
		List<Product> products = productRepository.findAll();
		assertNotNull(products);
		// assert products size > 0
		Product product = products.get(0);
		assertNotNull(product);
	}
}
