package com.springcloud.sbsuite.inventory.services;


import com.springcloud.sbsuite.inventory.domain.Inventory;

import java.util.List;

public interface InventoryService {

	public List<Inventory> fetchInventories();
}

