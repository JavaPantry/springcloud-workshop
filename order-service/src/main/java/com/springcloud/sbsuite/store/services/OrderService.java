package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.dto.CustomerDto;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import com.springcloud.sbsuite.store.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	public List<CustomerDto> fetchCustomenrs();
	public List<OrderHeaderDto> fetchOrderHeaders();
	public List<OrderLineDto> fetchOrderLines();

	public Optional<OrderLineDto> fetchOrderLineById(Long id);
	public Optional<OrderHeaderDto> fetchOrderHeaderById(Long id);
	public Optional<CustomerDto> fetchCustomerById(Long id);

	public Optional<OrderLineDto> saveOrderLine(OrderLineDto dto);
	public boolean deleteOrderLine(OrderLineDto dto);
	public Optional<OrderHeaderDto> saveOrderHeader(OrderHeaderDto dto);
	public boolean deleteOrderHeader(OrderHeaderDto dto);
	public Optional<CustomerDto> saveCustomer(CustomerDto dto);
	public boolean deleteCustomer(CustomerDto dto);

}

