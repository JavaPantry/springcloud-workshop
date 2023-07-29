package com.springcloud.sbsuite.inventory.services;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.inventory.mappers.InventoryMapper;
import com.springcloud.sbsuite.inventory.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryMapper inventoryMapper;

	@Override
	public List<InventoryDto> fetchInventories(){
		List<InventoryDto> inventories = inventoryRepository.findAll()
				.stream()
				.map(inventoryMapper::inventoryToInventoryDto)
				.collect(Collectors.toList());
		return inventories;
	}

	@Override
	public Optional<InventoryDto> fetchInventoryById(Long id) {
		return Optional.ofNullable(inventoryMapper.inventoryToInventoryDto(
						inventoryRepository.findById(id).orElse(null)
				)
		);
	}

	@Override
	public Optional<InventoryDto> fetchInventoryByProductId(Long productId){
		return Optional.ofNullable(inventoryMapper.inventoryToInventoryDto(
						inventoryRepository.findByProductId(productId).orElse(null)
				)
		);
	}

	@Override
	public List<InventoryDto> fetchByStoreId(Long storeId) {
		List<InventoryDto> inventories = inventoryRepository.findByStoreId(storeId)
				.stream()
				.map(inventoryMapper::inventoryToInventoryDto)
				.collect(Collectors.toList());
		return inventories;
	}

	@Override
	public Optional<InventoryDto> saveInventory(InventoryDto dto) {
		Inventory inventory = null;
		if(dto.getId() != null) {
			inventory = inventoryRepository.findById(dto.getId()).orElse(null);
			inventory.setQuantity(dto.getQuantity());
		}else {
			inventory = inventoryMapper.inventoryDtoToInventory(dto);
		}
		Inventory updated = inventoryRepository.save(inventory);
		return Optional.of(inventoryMapper.inventoryToInventoryDto(updated) );
	}

	@Override
	public boolean deleteInventory(InventoryDto dto) {
		// following mapping return inventory with id = null which cause exception during delete operation
		/*Inventory inventoryToDelete = inventoryMapper.inventoryDtoToInventory(dto);
		inventoryRepository.delete(inventoryToDelete);*/

		if (inventoryRepository.existsById(dto.getId())) {
			inventoryRepository.deleteById(dto.getId());
			return true;
		}
		return false;
	}
}
