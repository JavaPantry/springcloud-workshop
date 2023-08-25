package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.dto.AddressDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.mappers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class OrderHeaderMapperTest {

	@Autowired
	OrderHeaderMapper orderHeaderMapper;// = new OrderHeaderMapper();

	@Test
	public void testOrderHeaderMapping() {
		AddressDto fakeAddress = AddressDto.builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderLineDto orderLineDto1 = new OrderLineDto();
		orderLineDto1.setQuantityOrdered(1);orderLineDto1.setProductId(1L);
		OrderLineDto orderLineDto2 = new OrderLineDto();
		orderLineDto2.setQuantityOrdered(1);orderLineDto2.setProductId(2L);

		OrderHeaderDto orderHeaderDto = new OrderHeaderDto();
		orderHeaderDto.setName("test order");
		orderHeaderDto.setBillToAddress(fakeAddress);
		orderHeaderDto.setShippingAddress(fakeAddress);
		orderHeaderDto.setOrderStatus(OrderStatus.COMPLETED);

		orderHeaderDto.setOrderLines(new HashSet<>() {{
			add(orderLineDto1);
			add(orderLineDto2);
		}});

		orderLineDto1.setOrderHeader(orderHeaderDto);
		orderLineDto2.setOrderHeader(orderHeaderDto);

		OrderHeader orderHeader = orderHeaderMapper.dtoToEntity(orderHeaderDto, new CycleAvoidingMappingContext());
		assertNotNull(orderHeader);
		assertEquals(orderHeaderDto.getName(), orderHeader.getName());
		assertEquals(orderHeaderDto.getOrderStatus(), orderHeader.getOrderStatus());
		assertEquals(orderHeaderDto.getBillToAddress().getStreetAddress(), orderHeader.getBillToAddress().getStreetAddress());
		assertEquals(orderHeaderDto.getBillToAddress().getCity(), orderHeader.getBillToAddress().getCity());
		assertEquals(orderHeaderDto.getBillToAddress().getState(), orderHeader.getBillToAddress().getState());
		assertEquals(orderHeaderDto.getBillToAddress().getZipCode(), orderHeader.getBillToAddress().getZipCode());
		assertEquals(orderHeaderDto.getShippingAddress().getStreetAddress(), orderHeader.getShippingAddress().getStreetAddress());
		assertEquals(orderHeaderDto.getShippingAddress().getCity(), orderHeader.getShippingAddress().getCity());
		assertEquals(orderHeaderDto.getShippingAddress().getState(), orderHeader.getShippingAddress().getState());
		assertEquals(orderHeaderDto.getShippingAddress().getZipCode(), orderHeader.getShippingAddress().getZipCode());

		OrderHeaderDto orderHeaderDto2 = orderHeaderMapper.entityToDto(orderHeader, new CycleAvoidingMappingContext());
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

		OrderLineDto orderLineDto2_1 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 1L).findFirst().get();
		assertEquals(orderLineDto2_1.getProductId(), orderLineDto1.getProductId());
		assertEquals(orderLineDto2_1.getQuantityOrdered(), orderLineDto1.getQuantityOrdered());

		OrderLineDto orderLineDto2_2 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 2L).findFirst().get();
		assertEquals(orderLineDto2_2.getProductId(), orderLineDto2.getProductId());
		assertEquals(orderLineDto2_2.getQuantityOrdered(), orderLineDto2.getQuantityOrdered());

		System.out.println("orderHeaderDto2 " + orderHeaderDto2);
	}
}
