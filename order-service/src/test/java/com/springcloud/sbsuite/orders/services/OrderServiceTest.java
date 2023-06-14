package com.springcloud.sbsuite.orders.services;

import com.springcloud.sbsuite.dto.*;
import com.springcloud.sbsuite.orders.api.NotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
	void testGetOrderHeaderByIdNotFound() {
		assertNull(orderService.fetchOrderHeaderById(100L).orElse(null));
	}
	@Test
	void fetchCustomerById() {
		CustomerDto customerDto = orderService.fetchCustomerById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(customerDto);
		assertEquals(1L, customerDto.getId());
	}
	@Test
	void testGetCustomerByIdNotFound() {
		assertNull(orderService.fetchCustomerById(100L).orElse(null));
	}

	@Rollback
	@Transactional
	@Test
	void saveOrderLine() {
		OrderLineDto orderLine = new OrderLineDto().builder().quantityOrdered(11).productId(1L).build();
		OrderLineDto newOrderLine = orderService.saveOrderLine(orderLine).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderLine);
		assertEquals(11, newOrderLine.getQuantityOrdered());
		assertEquals(1L, newOrderLine.getProductId());
		orderLine = orderService.fetchOrderLineById(newOrderLine.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(orderLine);
		assertEquals(11, orderLine.getQuantityOrdered());
		assertEquals(1L, orderLine.getProductId());
	}

	@Rollback
	@Transactional
	@Test
	void deleteOrderLine() {
		OrderLineDto orderLine = new OrderLineDto().builder().quantityOrdered(11).productId(1L).build();
		OrderLineDto newOrderLine = orderService.saveOrderLine(orderLine).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderLine);
		orderService.deleteOrderLine(newOrderLine);
		orderLine = orderService.fetchOrderLineById(newOrderLine.getId()).orElse(null);
		assertNull(orderLine);
	}

	@Rollback
	@Transactional
	@Test
	void saveOrderHeader() {
		OrderHeaderDto orderHeader = new OrderHeaderDto().builder().orderStatus(OrderStatus.COMPLETED).build();
		OrderHeaderDto newOrderHeader = orderService.saveOrderHeader(orderHeader).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderHeader);
		assertEquals(OrderStatus.COMPLETED, newOrderHeader.getOrderStatus());
		orderHeader = orderService.fetchOrderHeaderById(newOrderHeader.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(orderHeader);
		assertEquals(OrderStatus.COMPLETED, orderHeader.getOrderStatus());
	}

	@Rollback
	@Transactional
	@Test
	void deleteOrderHeader() {
		OrderHeaderDto orderHeader = new OrderHeaderDto().builder().orderStatus(OrderStatus.COMPLETED).build();
		OrderHeaderDto newOrderHeader = orderService.saveOrderHeader(orderHeader).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderHeader);
		orderService.deleteOrderHeader(newOrderHeader);
		orderHeader = orderService.fetchOrderHeaderById(newOrderHeader.getId()).orElse(null);
		assertNull(orderHeader);
	}

	@Rollback
	@Transactional
	@Test
	void saveCustomer() {
		CustomerDto customer = new CustomerDto().builder().contact(new ContactDto("TestAvp","4161234567","TestAvp@b.com")).build();
		CustomerDto newCustomer = orderService.saveCustomer(customer).orElseThrow(NotFoundException::new);
		assertNotNull(newCustomer);
		assertEquals("TestAvp", newCustomer.getContact().getName());
		customer = orderService.fetchCustomerById(newCustomer.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(customer);
		assertEquals("TestAvp", customer.getContact().getName());
	}

	@Rollback
	@Transactional
	@Test
	void deleteCustomer() {
		CustomerDto customer = new CustomerDto().builder().contact(new ContactDto("TestAvp","4161234567","TestAvp@b.com")).build();
		CustomerDto newCustomer = orderService.saveCustomer(customer).orElseThrow(NotFoundException::new);
		assertNotNull(newCustomer);
		orderService.deleteCustomer(newCustomer);
		customer = orderService.fetchCustomerById(newCustomer.getId()).orElse(null);
		assertNull(customer);
	}
}