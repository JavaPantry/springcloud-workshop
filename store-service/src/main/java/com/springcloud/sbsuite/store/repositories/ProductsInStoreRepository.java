package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.ProductsInStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsInStoreRepository  extends JpaRepository<ProductsInStore, Long> {
}
