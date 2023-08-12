package com.springcloud.sbsuite.orders.services;

import com.springcloud.sbsuite.event.OrderPlacedEvent;
import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.CustomerDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.mappers.CustomerMapper;
import com.springcloud.sbsuite.orders.mappers.CycleAvoidingMappingContext;
import com.springcloud.sbsuite.orders.mappers.OrderHeaderMapper;
import com.springcloud.sbsuite.orders.mappers.OrderLineMapper;
import com.springcloud.sbsuite.orders.repositories.CustomerRepository;
import com.springcloud.sbsuite.orders.repositories.OrderHeaderRepository;
import com.springcloud.sbsuite.orders.repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

	@Autowired
	KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

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
												.map(entity -> orderHeaderMapper.entityToDto(entity, new CycleAvoidingMappingContext()))
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
															.map(entity ->orderLineMapper.entityToDto(entity, new CycleAvoidingMappingContext()))
															.collect(Collectors.toList());
		return orderLines;
	}

	@Override
	public Optional<OrderLineDto> fetchOrderLineById(Long id) {
		return Optional.ofNullable(orderLineMapper.entityToDto(
											orderLineRepository.findById(id).orElse(null)
											, new CycleAvoidingMappingContext()
											)
		);
	}

	@Override
	public Optional<OrderHeaderDto> fetchOrderHeaderById(Long id) {
		return Optional.ofNullable(orderHeaderMapper.entityToDto(
						orderHeaderRepository.findById(id).orElse(null), new CycleAvoidingMappingContext()
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
			orderLine = orderLineMapper.dtoToEntity(dto, new CycleAvoidingMappingContext());
		}
		OrderLine updated = orderLineRepository.save(orderLine);
		return Optional.of(orderLineMapper.entityToDto(updated, new CycleAvoidingMappingContext()) );
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
			orderHeader = orderHeaderMapper.dtoToEntity(dto, new CycleAvoidingMappingContext());
		}
		OrderHeader updated = orderHeaderRepository.save(orderHeader);
		return Optional.of(orderHeaderMapper.entityToDto(updated, new CycleAvoidingMappingContext()) );
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

	@Override
	public String placeOrder(Long productId, Integer quantity) {
		kafkaTemplate.send("orderTopic", new OrderPlacedEvent(productId, quantity));
		return "Event sent successfully";
	}


}
