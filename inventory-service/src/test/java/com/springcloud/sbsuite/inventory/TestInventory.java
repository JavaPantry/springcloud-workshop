package com.springcloud.sbsuite.inventory;


import com.springcloud.sbsuite.inventory.domain.Inventory;
import com.springcloud.sbsuite.inventory.repositories.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @ComponentScan(basePackages = {"com.sbsuite.sbsuitedata.bootstrap"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestInventory {

	@Autowired
	InventoryRepository inventoryRepository;

	@Test
	void testFetchProducts() {
		List<Inventory> inventories = inventoryRepository.findAll();
		assertNotNull(inventories);
		// assert products size > 0
		Inventory inventory = inventories.get(0);
		assertNotNull(inventory);
	}
}
