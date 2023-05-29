package com.springcloud.sbsuite.inventory.repositories;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	public Optional<Inventory> findByProductId(Long productId);
}
