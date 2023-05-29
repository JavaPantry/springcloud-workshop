package com.springcloud.sbsuite.inventory.api;

import com.springcloud.sbsuite.inventory.dto.InventoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class InventoryControllerTest {

	@Autowired
	InventoryController inventoryController;
	@Test
	void getInventory() {
		List<InventoryDto> inventories = inventoryController.getInventory();
		assertNotNull(inventories);
		// assert inventories size > 0
		InventoryDto inventory = inventories.get(0);
		assertNotNull(inventory);
		assertEquals(1, inventory.getProductId());
	}

	@Test
	void getInventoryById() {
		InventoryDto inventory = inventoryController.getInventoryById(1L);
		assertNotNull(inventory);
		assertEquals(1, inventory.getProductId());
	}

	@Test
	void getInventoryByProductId() {
		InventoryDto inventory = inventoryController.getInventoryByProductId(1L);
		assertNotNull(inventory);
		assertEquals(1, inventory.getProductId());
		assertEquals(10, inventory.getQuantity());
	}

	@Test
	void testConfigVar() {
	}
}