package com.springcloud.sbsuite.inventory.services;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import com.springcloud.sbsuite.inventory.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;


	@Override
	public List<Inventory> fetchInventories(){
		List<Inventory> inventories = inventoryRepository.findAll();
		return inventories;
	}
}
