package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.dto.AddressDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.mappers.*;
import com.springcloud.sbsuite.orders.repositories.OrderLineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MapperTest {

	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineMapper orderLineMapper;

	@Autowired
	OrderHeaderMapperStruct orderHeaderMapperStruct;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	DateMapper dateMapper;

	@Test
	public void testOrderLine() {
		OrderLine orderLine = new OrderLine();
		orderLine.setId(1L);
		orderLine.setProductId(1L);
		orderLine.setQuantityOrdered(1);
		OrderLineDto orderLineDto = orderLineMapper.entityToDto(orderLine, new CycleAvoidingMappingContext());
		assertNotNull(orderLineDto);
		assertEquals(orderLine.getId(), orderLineDto.getId());
		assertEquals(orderLine.getProductId(), orderLineDto.getProductId());
		assertEquals(orderLine.getQuantityOrdered(), orderLineDto.getQuantityOrdered());
	}

	/*@Test
	public void testOrderHeaderMapping() {
		AddressDto fakeAddress = AddressDto.builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderLineDto orderLineDto1 = OrderLineDto.builder().quantityOrdered(1).productId(1L).build();
		OrderLineDto orderLineDto2 = OrderLineDto.builder().quantityOrdered(1).productId(2L).build();

		OrderHeaderDto orderHeaderDto = OrderHeaderDto.builder()
				.name("test order")
				.billToAddress(fakeAddress)
				.shippingAddress(fakeAddress)
				.orderStatus(OrderStatus.COMPLETED)
				.orderLines(new ArrayList<>() {{
					add(orderLineDto1);
					add(orderLineDto2);
				}})
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.build();

		// test bidirectional mapping
		orderLineDto1.setOrderHeader(orderHeaderDto);
		orderLineDto2.setOrderHeader(orderHeaderDto);

 		OrderHeader orderHeader = orderHeaderMapperStruct.dtoToEntity(orderHeaderDto, new CycleAvoidingMappingContext());
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

		OrderHeaderDto orderHeaderDto2 = orderHeaderMapperStruct.entityToDto(orderHeader, new CycleAvoidingMappingContext());
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
		List<OrderLineDto> orderLines = orderHeaderDto2.getOrderLines();
		assertEquals(2, orderLines.size());

		OrderLineDto orderLineDto2_1 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 1L).findFirst().get();
		assertEquals(orderLineDto2_1.getProductId(), orderLineDto1.getProductId());
		assertEquals(orderLineDto2_1.getQuantityOrdered(), orderLineDto1.getQuantityOrdered());

		OrderLineDto orderLineDto2_2 = orderLines.stream().filter(orderLineDto -> orderLineDto.getProductId() == 2L).findFirst().get();
		assertEquals(orderLineDto2_2.getProductId(), orderLineDto2.getProductId());
		assertEquals(orderLineDto2_2.getQuantityOrdered(), orderLineDto2.getQuantityOrdered());

		System.out.println("orderHeaderDto2 " + orderHeaderDto2);
	}*/
}
