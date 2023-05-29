package com.springcloud.sbsuite.inventory.services;


import com.springcloud.sbsuite.inventory.dto.InventoryDto;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

	public List<InventoryDto> fetchInventories();
	public Optional<InventoryDto> fetchInventoryById(Long id);
	public Optional<InventoryDto> fetchInventoryByProductId(Long id);

	public Optional<InventoryDto> saveInventory(InventoryDto entity);
	public boolean deleteInventory(InventoryDto entity);
}

