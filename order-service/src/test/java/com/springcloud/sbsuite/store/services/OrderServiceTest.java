package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.api.NotFoundException;
import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {

	@Autowired
	OrderService orderService;

//	@Test
	void fetchCustomenrs() {
	}

//	@Test
	void fetchOrderHeaders() {
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
}