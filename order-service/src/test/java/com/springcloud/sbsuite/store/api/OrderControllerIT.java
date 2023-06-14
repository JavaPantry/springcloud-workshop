package com.springcloud.sbsuite.store.api;

import com.springcloud.sbsuite.dto.OrderLineDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderControllerIT {

	@Autowired
	OrderController orderController;

	@Test
	void getOrderLines() {
		List<OrderLineDto> lines = orderController.getOrderLines();
		assertNotNull(lines);
		OrderLineDto line = lines.get(0);
		assertNotNull(line);
		assertEquals(1, line.getQuantityOrdered());
	}

	@Test
	void getOrderLineById() {
		OrderLineDto line = orderController.getOrderLine(1L);
		assertNotNull(line);
		assertEquals(1, line.getQuantityOrdered());
	}

	@Test
	void getOrderLineByIdNotFound() {
		assertThrows(NotFoundException.class, () -> {
			orderController.getOrderLine(100L);
		});
	}
}