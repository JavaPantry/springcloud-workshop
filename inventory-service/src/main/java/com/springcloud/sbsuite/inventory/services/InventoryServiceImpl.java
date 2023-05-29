package com.springcloud.sbsuite.inventory.services;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import com.springcloud.sbsuite.inventory.dto.InventoryDto;
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
}
