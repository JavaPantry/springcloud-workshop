package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.repositories.CustomerRepository;
import com.springcloud.sbsuite.store.repositories.OrderHeaderRepository;
import com.springcloud.sbsuite.store.repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	OrderLineRepository orderLineRepository;

	@Override
	public List<Customer> fetchCustomenrs(){
		List<Customer> customenrs = customerRepository.findAll();
		return customenrs;
	}

	@Override
	public List<OrderHeader> fetchOrderHeaders() {
		List<OrderHeader> orderHeaders = orderHeaderRepository.findAll();
		return null;
	}

	@Override
	public List<OrderLine> fetchOrderLines() {
		List<OrderLine> orderLines = orderLineRepository.findAll();
		return null;
	}

}
