package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.mappers.OrderLineMapper;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @ SpringBootTest
public class MapperTest {
	private OrderLineMapper mapper = Mappers.getMapper(OrderLineMapper.class);
	// @ Test
	public void testOrderLine() {
		OrderLine orderLine = new OrderLine();
		orderLine.setId(1L);
		orderLine.setProductId(1L);
		orderLine.setQuantityOrdered(1);
		OrderLineDto orderLineDto = mapper.orderLineToOrderLineDto(orderLine);
		assertNotNull(orderLineDto);
		assertEquals(orderLine.getId(), orderLineDto.getId());
		assertEquals(orderLine.getProductId(), orderLineDto.getProductId());
		assertEquals(orderLine.getQuantityOrdered(), orderLineDto.getQuantityOrdered());
	}
}
