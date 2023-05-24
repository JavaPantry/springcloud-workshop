package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 12/5/21.
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
