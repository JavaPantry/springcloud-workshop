package com.springcloud.sbsuite.store.eclient2;

import com.springcloud.sbsuite.dto.InventoryDto;

import java.util.List;

public interface InventoryRestService {
	String getInventory();
	List<InventoryDto> getInventoryInStore(Long storeId);
}
