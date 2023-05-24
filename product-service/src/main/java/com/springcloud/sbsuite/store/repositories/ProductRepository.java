package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 12/11/21.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);
}
