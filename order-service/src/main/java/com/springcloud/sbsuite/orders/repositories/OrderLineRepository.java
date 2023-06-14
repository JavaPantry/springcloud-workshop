package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.orders.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
