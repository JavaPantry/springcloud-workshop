package com.springcloud.sbsuite.inventory.mappers;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import com.springcloud.sbsuite.inventory.dto.InventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface InventoryMapper {
	Inventory inventoryDtoToInventory(InventoryDto inventoryDto);
	InventoryDto inventoryToInventoryDto(Inventory inventory);

}
