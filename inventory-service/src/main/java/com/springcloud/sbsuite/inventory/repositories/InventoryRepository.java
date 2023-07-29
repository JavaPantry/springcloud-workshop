package com.springcloud.sbsuite.inventory.repositories;

import com.springcloud.sbsuite.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	public Optional<Inventory> findByProductId(Long productId);
	public List<Inventory> findByStoreId(Long storeId);


//	@Query("SELECT e FROM Inventory e WHERE e.field1 IN :arrayOfField1Values AND e.field2 IN :arrayOfField2Values")
//	List<YourEntity> findByArrayOfFields(@Param("arrayOfField1Values") List<String> arrayOfField1Values,
//	                                     @Param("arrayOfField2Values") List<Integer> arrayOfField2Values);

}
