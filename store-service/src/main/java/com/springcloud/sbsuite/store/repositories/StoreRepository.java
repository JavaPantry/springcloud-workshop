package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository  extends JpaRepository<Store, Long> {
}
