package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Customer;
import com.springcloud.sbsuite.store.domain.OrderHeader;
import com.springcloud.sbsuite.store.domain.OrderLine;
import dto.CustomerDto;
import dto.OrderHeaderDto;
import dto.OrderLineDto;
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

	@Override
	public Optional<OrderHeaderDto> fetchOrderHeaderById(Long id) {
		return Optional.ofNullable(orderHeaderMapper.orderHeaderToOrderHeaderDto(
						orderHeaderRepository.findById(id).orElse(null)
				)
		);
	}

	@Override
	public Optional<CustomerDto> fetchCustomerById(Long id) {
		return Optional.ofNullable(customerMapper.customerToCustomerDto(
								customerRepository.findById(id).orElse(null)
				)
		);
	}

	@Override
	public Optional<OrderLineDto> saveOrderLine(OrderLineDto dto) {
		OrderLine orderLine = null;
		if(dto.getId() != null) {
			orderLine = orderLineRepository.findById(dto.getId()).orElse(null);
			orderLine.setQuantityOrdered(dto.getQuantityOrdered());
		}else {
			orderLine = orderLineMapper.orderLineDtoToOrderLine(dto);
		}
		OrderLine updated = orderLineRepository.save(orderLine);
		return Optional.of(orderLineMapper.orderLineToOrderLineDto(updated) );
	}

	@Override
	public boolean deleteOrderLine(OrderLineDto dto) {
		if(orderLineRepository.existsById(dto.getId())) {
			orderLineRepository.deleteById(dto.getId());
			return true;
		}
		return false;
	}

	@Override
	public Optional<OrderHeaderDto> saveOrderHeader(OrderHeaderDto dto) {
		OrderHeader orderHeader = null;
		if(dto.getId() != null) {
			orderHeader = orderHeaderRepository.findById(dto.getId()).orElse(null);
			orderHeader.setOrderStatus(dto.getOrderStatus());
		}else {
			orderHeader = orderHeaderMapper.orderHeaderDtoToOrderHeader((dto));
		}
		OrderHeader updated = orderHeaderRepository.save(orderHeader);
		return Optional.of(orderHeaderMapper.orderHeaderToOrderHeaderDto(updated) );
	}

	@Override
	public boolean deleteOrderHeader(OrderHeaderDto dto) {
		if(orderHeaderRepository.existsById(dto.getId())) {
			orderHeaderRepository.deleteById(dto.getId());
			return true;
		}
		return false;
	}

	@Override
	public Optional<CustomerDto> saveCustomer(CustomerDto dto) {
		Customer customer = null;
		if(dto.getId() != null) {
			customer = customerRepository.findById(dto.getId()).orElse(null);
			customer.getContact().setName(dto.getContact().getName());
		}else {
			customer = customerMapper.customerDtoToCustomer((dto));
		}
		Customer updated = customerRepository.save(customer);
		return Optional.of(customerMapper.customerToCustomerDto(updated) );

	}

	@Override
	public boolean deleteCustomer(CustomerDto dto) {
		if(customerRepository.existsById(dto.getId())) {
			customerRepository.deleteById(dto.getId());
			return true;
		}
		return false;
	}


}
