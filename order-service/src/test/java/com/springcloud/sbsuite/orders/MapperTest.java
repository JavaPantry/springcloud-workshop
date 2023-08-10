package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.dto.AddressDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.mappers.CustomerMapper;
import com.springcloud.sbsuite.orders.mappers.DateMapper;
import com.springcloud.sbsuite.orders.mappers.OrderHeaderMapper;
import com.springcloud.sbsuite.orders.mappers.OrderLineMapper;
import com.springcloud.sbsuite.orders.repositories.OrderHeaderRepository;
import com.springcloud.sbsuite.orders.repositories.OrderLineRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MapperTest {

	@Autowired
	OrderLineRepository orderLineRepository;

	@Autowired
	OrderLineMapper orderLineMapper;

	@Autowired
	OrderHeaderMapper orderHeaderMapper;

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
		OrderLineDto orderLineDto = orderLineMapper.orderLineToOrderLineDto(orderLine);
		assertNotNull(orderLineDto);
		assertEquals(orderLine.getId(), orderLineDto.getId());
		assertEquals(orderLine.getProductId(), orderLineDto.getProductId());
		assertEquals(orderLine.getQuantityOrdered(), orderLineDto.getQuantityOrdered());
	}

	@Test
	public void testOrderHeaderMapping() {
		AddressDto fakeAddress = AddressDto.builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderLineDto orderLine1 = OrderLineDto.builder().quantityOrdered(1).productId(1L).build();
		OrderLineDto orderLine2 = OrderLineDto.builder().quantityOrdered(1).productId(2L).build();

		OrderHeaderDto orderHeaderDto = OrderHeaderDto.builder()
				.name("test order")
				.billToAddress(fakeAddress)
				.shippingAddress(fakeAddress)
				.orderStatus(OrderStatus.COMPLETED)
				.orderLines(new HashSet<OrderLineDto>() {{
					add(orderLine1);
					add(orderLine2);
				}})
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.build();

		OrderHeader orderHeader = orderHeaderMapper.orderHeaderDtoToOrderHeader(orderHeaderDto);
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

		OrderHeaderDto orderHeaderDto2 = orderHeaderMapper.orderHeaderToOrderHeaderDto(orderHeader);
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

		System.out.println("orderHeaderDto2 " + orderHeaderDto2);
	}
}
