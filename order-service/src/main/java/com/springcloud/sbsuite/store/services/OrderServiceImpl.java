package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import com.springcloud.sbsuite.store.mappers.OrderLineMapper;
import com.springcloud.sbsuite.store.repositories.CustomerRepository;
import com.springcloud.sbsuite.store.repositories.OrderHeaderRepository;
import com.springcloud.sbsuite.store.repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineMapper orderLineMapper;

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
	public List<OrderLineDto> fetchOrderLines() {
		List<OrderLineDto> orderLines = orderLineRepository.findAll()
															.stream()
															.map(orderLineMapper::orderLineToOrderLineDto)
															.collect(Collectors.toList());
		return orderLines;
	}

	@Override
	public Optional<OrderLineDto> fetchOrderLineById(Long id) {
		return Optional.ofNullable(orderLineMapper.orderLineToOrderLineDto(
											orderLineRepository.findById(id).orElse(null)
											)
		);
	}


}
