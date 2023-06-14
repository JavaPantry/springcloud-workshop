package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.orders.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 12/5/21.
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
