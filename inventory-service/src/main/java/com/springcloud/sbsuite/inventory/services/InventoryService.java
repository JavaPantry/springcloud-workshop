package com.springcloud.sbsuite.inventory.services;


import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.inventory.domain.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

	public List<InventoryDto> fetchInventories();
	public Optional<InventoryDto> fetchInventoryById(Long id);
	public Optional<InventoryDto> fetchInventoryByProductId(Long id);
	public List<InventoryDto> fetchByStoreId(Long storeId);

	public Optional<InventoryDto> saveInventory(InventoryDto entity);
	public boolean deleteInventory(InventoryDto entity);
}

