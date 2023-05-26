package com.springcloud.sbsuite.store.services;


import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import com.springcloud.sbsuite.store.dto.OrderLineDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	public List<Customer> fetchCustomenrs();
	public List<OrderHeaderDto> fetchOrderHeaders();
	public List<OrderLineDto> fetchOrderLines();

	public Optional<OrderLineDto> fetchOrderLineById(Long id);
}

