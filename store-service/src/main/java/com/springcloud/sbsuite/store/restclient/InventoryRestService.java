package com.springcloud.sbsuite.store.restclient;

import com.springcloud.sbsuite.dto.InventoryDto;

import java.util.List;

public interface InventoryRestService {
	String getInventory();
	List<InventoryDto> getInventoryInStore(Long storeId);
}
