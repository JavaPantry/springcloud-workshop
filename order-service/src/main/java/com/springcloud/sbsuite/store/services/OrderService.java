package com.springcloud.sbsuite.store.services;


import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.domain.OrderLine;

import java.util.List;

public interface OrderService {

	public List<Customer> fetchCustomenrs();
	public List<OrderHeader> fetchOrderHeaders();
	public List<OrderLine> fetchOrderLines();

}

