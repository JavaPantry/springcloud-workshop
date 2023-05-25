package com.springcloud.sbsuite.store;

import com.springcloud.sbsuite.store.domain.OrderLine;
import com.springcloud.sbsuite.store.dto.OrderLineDto;
import com.springcloud.sbsuite.store.mappers.OrderLineMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
