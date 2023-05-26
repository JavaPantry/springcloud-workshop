package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.CustomerDto;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import com.springcloud.sbsuite.store.mappers.CustomerMapper;
import com.springcloud.sbsuite.store.mappers.OrderHeaderMapper;
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

	@Autowired
	OrderHeaderMapper orderHeaderMapper;

	@Autowired
	CustomerMapper customerMapper;

	@Override
	public List<CustomerDto> fetchCustomenrs(){
		List<CustomerDto> customenrs = customerRepository.findAll()
				.stream()
				.map(customerMapper::customerToCustomerDto)
				.collect(Collectors.toList());
		return customenrs;
	}

	@Override
	public List<OrderHeaderDto> fetchOrderHeaders() {

		List<OrderHeader> headers = orderHeaderRepository.findAll();

		List<OrderHeaderDto> orderHeaders = headers.stream()
												.map(orderHeaderMapper::orderHeaderToOrderHeaderDto)
												.collect(Collectors.toList());

		/*List<OrderHeaderDto> orderHeaders = orderHeaderRepository.findAll()
				.stream()
				.map(orderHeaderMapper::orderHeaderToOrderHeaderDto)
				.collect(Collectors.toList());*/
		return orderHeaders;
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
