package com.springcloud.sbsuite.orders.services;

import com.springcloud.sbsuite.dto.*;
import com.springcloud.sbsuite.orders.api.NotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {

	@Autowired
	OrderService orderService;

	/*@Test
	void fetchCustomenrs() {
		List<CustomerDto> customers = orderService.fetchCustomenrs();
		assertNotNull(customers);
		CustomerDto customer = customers.get(0);
		assertNotNull(customer);
		assertEquals("Alexey Pashin", customer.getContact().getName());
	}*/

	/*@Test
	void fetchOrderHeaders() {
		List<OrderHeaderDto> orderHeaders = orderService.fetchOrderHeaders();
		assertNotNull(orderHeaders);
		OrderHeaderDto orderHeader = orderHeaders.get(0);
		assertNotNull(orderHeader);
		assertEquals("order1", orderHeader.getName());
	}*/

	/*@Test
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
	}*/

	@Test
	void testGetOrderLineByIdNotFound() {
		assertNull(orderService.fetchOrderLineById(100L).orElse(null));
	}

	/*@Test
	void fetchOrderHeaderById() {
		OrderHeaderDto orderHeaderDto = orderService.fetchOrderHeaderById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(orderHeaderDto);
		assertEquals(OrderStatus.COMPLETED, orderHeaderDto.getOrderStatus());
	}*/
	@Test
	void testGetOrderHeaderByIdNotFound() {
		assertNull(orderService.fetchOrderHeaderById(100L).orElse(null));
	}
	/*@Test
	void fetchCustomerById() {
		CustomerDto customerDto = orderService.fetchCustomerById(1L).orElseThrow(NotFoundException::new);
		assertNotNull(customerDto);
		assertEquals(1L, customerDto.getId());
	}*/
	@Test
	void testGetCustomerByIdNotFound() {
		assertNull(orderService.fetchCustomerById(100L).orElse(null));
	}

	/*@Rollback
	@Transactional
	@Test
	void saveOrderLine() {
		OrderLineDto orderLine = new OrderLineDto();
		orderLine.setQuantityOrdered(1);orderLine.setProductId(1L);

		OrderLineDto newOrderLine = orderService.saveOrderLine(orderLine).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderLine);
		assertEquals(11, newOrderLine.getQuantityOrdered());
		assertEquals(1L, newOrderLine.getProductId());
		orderLine = orderService.fetchOrderLineById(newOrderLine.getId()).orElseThrow(NotFoundException::new);
		assertNotNull(orderLine);
		assertEquals(11, orderLine.getQuantityOrdered());
		assertEquals(1L, orderLine.getProductId());
	}*/

	@Rollback
	@Transactional
	@Test
	void deleteOrderLine() {
		OrderLineDto orderLine = new OrderLineDto();
		orderLine.setQuantityOrdered(1);orderLine.setProductId(1L);

		OrderLineDto newOrderLine = orderService.saveOrderLine(orderLine).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderLine);
		orderService.deleteOrderLine(newOrderLine);
		orderLine = orderService.fetchOrderLineById(newOrderLine.getId()).orElse(null);
		assertNull(orderLine);
	}

	@Transactional
	@Test
	void saveOrderHeader() {


		AddressDto fakeAddress = AddressDto.builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderLineDto orderLine1 = new OrderLineDto();
		orderLine1.setQuantityOrdered(1);orderLine1.setProductId(1L);
		OrderLineDto orderLine2 = new OrderLineDto();
		orderLine2.setQuantityOrdered(1);orderLine2.setProductId(2L);

		OrderHeaderDto orderHeaderDto = new OrderHeaderDto();
		orderHeaderDto.setName("test order");
		orderHeaderDto.setBillToAddress(fakeAddress);
		orderHeaderDto.setShippingAddress(fakeAddress);
		orderHeaderDto.setOrderStatus(OrderStatus.COMPLETED);

		orderHeaderDto.setOrderLines(new HashSet<>() {{
			add(orderLine1);
			add(orderLine2);
		}});

		orderLine1.setOrderHeader(orderHeaderDto);
		orderLine2.setOrderHeader(orderHeaderDto);

		OrderHeaderDto orderHeaderDto2 = orderService.saveOrderHeader(orderHeaderDto).orElseThrow(NotFoundException::new);

		assertNotNull(orderHeaderDto2);
		assertEquals(orderHeaderDto.getName(), orderHeaderDto2.getName());
		assertEquals(orderHeaderDto.getOrderStatus(), orderHeaderDto2.getOrderStatus());
		assertEquals(orderHeaderDto.getBillToAddress().getStreetAddress(), orderHeaderDto2.getBillToAddress().getStreetAddress());
		assertEquals(orderHeaderDto.getBillToAddress().getCity(), orderHeaderDto2.getBillToAddress().getCity());
		assertEquals(orderHeaderDto.getBillToAddress().getState(), orderHeaderDto2.getBillToAddress().getState());
		assertEquals(orderHeaderDto.getBillToAddress().getZipCode(), orderHeaderDto2.getBillToAddress().getZipCode());
		assertEquals(orderHeaderDto.getShippingAddress().getStreetAddress(), orderHeaderDto2.getShippingAddress().getStreetAddress());
		assertEquals(orderHeaderDto.getShippingAddress().getCity(), orderHeaderDto2.getShippingAddress().getCity());
		assertEquals(orderHeaderDto.getShippingAddress().getState(), orderHeaderDto2.getShippingAddress().getState());
		assertEquals(orderHeaderDto.getShippingAddress().getZipCode(), orderHeaderDto2.getShippingAddress().getZipCode());

		assertNotNull(orderHeaderDto2.getOrderLines());
		Set<OrderLineDto> orderLines = orderHeaderDto2.getOrderLines();
		assertEquals(2, orderLines.size());

		OrderLineDto orderLineDto1 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 1L).findFirst().get();
		assertEquals(orderLine1.getProductId(), orderLineDto1.getProductId());
		assertEquals(orderLine1.getQuantityOrdered(), orderLineDto1.getQuantityOrdered());

		OrderLineDto orderLineDto2 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 2L).findFirst().get();
		assertEquals(orderLine2.getProductId(), orderLineDto2.getProductId());
		assertEquals(orderLine2.getQuantityOrdered(), orderLineDto2.getQuantityOrdered());
	}

	@Rollback
	@Transactional
	@Test
	void deleteOrderHeader() {
		OrderHeaderDto orderHeaderDto = new OrderHeaderDto();
		orderHeaderDto.setName("test order");
		orderHeaderDto.setOrderStatus(OrderStatus.COMPLETED);

		OrderHeaderDto newOrderHeader = orderService.saveOrderHeader(orderHeaderDto).orElseThrow(NotFoundException::new);
		assertNotNull(newOrderHeader);
		orderService.deleteOrderHeader(newOrderHeader);
		orderHeaderDto = orderService.fetchOrderHeaderById(newOrderHeader.getId()).orElse(null);
		assertNull(orderHeaderDto);
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