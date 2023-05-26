package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.dto.CustomerDto;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import com.springcloud.sbsuite.store.dto.OrderLineDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	public List<CustomerDto> fetchCustomenrs();
	public List<OrderHeaderDto> fetchOrderHeaders();
	public List<OrderLineDto> fetchOrderLines();

	public Optional<OrderLineDto> fetchOrderLineById(Long id);
}

