package com.springcloud.sbsuite.inventory.services;

import com.springcloud.sbsuite.inventory.api.NotFoundException;
import com.springcloud.sbsuite.dto.InventoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

	@Rollback
	@Transactional
	@Test
	void saveNewInventory() {
		InventoryDto inventory = new InventoryDto().builder().productId(6L).quantity(20).build();
		InventoryDto newInventory = inventoryService.saveInventory(inventory).orElseThrow(NotFoundException::new);
		assertNotNull(newInventory);
		assertEquals(6L, newInventory.getProductId());
		assertEquals(20, newInventory.getQuantity());
		inventory = inventoryService.fetchInventoryByProductId(6L).orElseThrow(NotFoundException::new);
		assertNotNull(inventory);
		assertEquals(6L, inventory.getProductId());
		assertEquals(20, inventory.getQuantity());
	}

	@Rollback
	@Transactional
	@Test
	void saveInventory() {
		InventoryDto inventory = inventoryService.fetchInventoryByProductId(1L).orElseThrow(NotFoundException::new);
		inventory.setQuantity(100);
		InventoryDto updatedInventory = inventoryService.saveInventory(inventory).orElseThrow(NotFoundException::new);
		assertNotNull(updatedInventory);
		assertEquals(100, updatedInventory.getQuantity());
		inventory = inventoryService.fetchInventoryByProductId(1L).orElseThrow(NotFoundException::new);
		assertEquals(100, updatedInventory.getQuantity());
	}

	@Test
	void deleteInventory() {
		InventoryDto inventory = new InventoryDto().builder().productId(6L).quantity(20).build();
		InventoryDto newInventory = inventoryService.saveInventory(inventory).orElseThrow(NotFoundException::new);
		assertNotNull(newInventory);
		assertEquals(6L, newInventory.getProductId());
		assertEquals(20, newInventory.getQuantity());
		inventory = inventoryService.fetchInventoryByProductId(6L).orElseThrow(NotFoundException::new);
		assertNotNull(inventory);
		assertEquals(6L, inventory.getProductId());
		assertEquals(20, inventory.getQuantity());
		inventoryService.deleteInventory(inventory);
		inventory = inventoryService.fetchInventoryByProductId(6L).orElse(null);
		assertNull(inventory);
	}
}