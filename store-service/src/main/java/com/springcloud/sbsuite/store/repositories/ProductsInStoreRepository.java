package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.ProductsInStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsInStoreRepository  extends JpaRepository<ProductsInStore, Long> {
	//find list of ProductsInStore by store id
	//select * from ProductsInStore where store_id = storeId
	public List<ProductsInStore> findByStoreId(Long storeId);

}
