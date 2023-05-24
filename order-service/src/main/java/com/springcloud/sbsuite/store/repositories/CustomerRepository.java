package com.springcloud.sbsuite.store.repositories;

import com.springcloud.sbsuite.store.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 5/21/22.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
