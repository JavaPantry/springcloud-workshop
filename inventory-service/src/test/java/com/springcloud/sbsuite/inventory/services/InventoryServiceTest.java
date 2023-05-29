package com.springcloud.sbsuite.inventory.services;

import com.springcloud.sbsuite.inventory.api.NotFoundException;
import com.springcloud.sbsuite.inventory.dto.InventoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryServiceTest {

	@Autowired
	InventoryService inventoryService;
	@Test
	void fetchInventories() {
		List<InventoryDto> inventories = inventoryService.fetchInventories();
		assertNotNull(inventories);
		// assert inventories size > 0
		InventoryDto inventory = inventories.get(0);
		assertNotNull(inventory);
		assertEquals(1L, inventory.getProductId());
		assertEquals(10, inventory.getQuantity());
	}

	@Test
	void fetchInventoryById() {
		InventoryDto inventory = inventoryService.fetchInventoryById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(inventory);
		assertEquals(1L, inventory.getProductId());
		assertEquals(10, inventory.getQuantity());
	}

	@Test
	void testGetInventoryByIdNotFound() {
		assertNull(inventoryService.fetchInventoryById(100L).orElse(null));
	}
	@Test
	void fetchInventoryByProductId() {
		InventoryDto inventory = inventoryService.fetchInventoryByProductId(1L).orElseThrow(NotFoundException::new);
		assertNotNull(inventory);
		assertEquals(1L, inventory.getProductId());
		assertEquals(10, inventory.getQuantity());
	}

	@Test
	void testGetInventoryByProductIdNotFound() {
		assertNull(inventoryService.fetchInventoryByProductId(100L).orElse(null));
	}
}