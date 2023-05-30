package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.api.NotFoundException;
import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.CustomerDto;
import com.springcloud.sbsuite.store.dto.OrderHeaderDto;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import com.springcloud.sbsuite.store.dto.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Test
	void fetchCustomenrs() {
		List<CustomerDto> customers = orderService.fetchCustomenrs();
		assertNotNull(customers);
		CustomerDto customer = customers.get(0);
		assertNotNull(customer);
		assertEquals("Alexey Pashin", customer.getContact().getName());
	}

	@Test
	void fetchOrderHeaders() {
		List<OrderHeaderDto> orderHeaders = orderService.fetchOrderHeaders();
		assertNotNull(orderHeaders);
		OrderHeaderDto orderHeader = orderHeaders.get(0);
		assertNotNull(orderHeader);
		assertEquals("order1", orderHeader.getName());
	}

	@Test
	void fetchOrderLines() {
		List<OrderLineDto> orderLines = orderService.fetchOrderLines();
		assertNotNull(orderLines);
		OrderLineDto orderLine = orderLines.get(0);
		assertNotNull(orderLine);
		assertEquals(1, orderLine.getQuantityOrdered());
	}

	@Test
	void testGetOrderLineById() {
		OrderLineDto orderLine = orderService.fetchOrderLineById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(orderLine);
		assertEquals(1, orderLine.getQuantityOrdered());
	}

	@Test
	void testGetOrderLineByIdNotFound() {
		assertNull(orderService.fetchOrderLineById(100L).orElse(null));
	}

	@Test
	void fetchOrderHeaderById() {
		OrderHeaderDto orderHeaderDto = orderService.fetchOrderHeaderById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(orderHeaderDto);
		assertEquals(OrderStatus.COMPLETED, orderHeaderDto.getOrderStatus());
	}

	@Test
	void fetchCustomerById() {
		CustomerDto customerDto = orderService.fetchCustomerById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(customerDto);
		assertEquals(1L, customerDto.getId());
	}
}