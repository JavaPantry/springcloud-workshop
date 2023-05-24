package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
