package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.dto.AddressDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.orders.mappers.OrderHeaderMapper;
import com.springcloud.sbsuite.orders.mappers.OrderLineMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashSet;

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

	@Test
	public void testAddressMappingInOrderHeader() {
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

		OrderHeaderMapper orderHeaderMapper = Mappers.getMapper(OrderHeaderMapper.class);
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
		System.out.println("Done");

		OrderHeaderDto orderHeaderDto2 = orderHeaderMapper.orderHeaderToOrderHeaderDto(orderHeader);
		System.out.println("orderHeaderDto2 " + orderHeaderDto2);
	}
	@Test
	public void testOrderHeader() {
		AddressDto fakeAddress = new AddressDto().builder()
				.streetAddress("123 Main St")
				.city("Anytown")
				.state("NY")
				.zipCode("12345").build();

		OrderLineDto orderLine1 = new OrderLineDto().builder().quantityOrdered(1).productId(1L).build();
		OrderLineDto orderLine2 = new OrderLineDto().builder().quantityOrdered(1).productId(2L).build();

		OrderHeaderDto orderHeaderDto = new OrderHeaderDto().builder()
				.name("test order")
				.orderLines(new HashSet<OrderLineDto>() {{
					add(orderLine1);
					add(orderLine2);
				}})
				.billToAddress(fakeAddress)
				.shippingAddress(fakeAddress)
				.orderStatus(OrderStatus.COMPLETED)
				.build();

		OrderHeaderMapper orderHeaderMapper = Mappers.getMapper(OrderHeaderMapper.class);
		OrderHeader orderHeader = orderHeaderMapper.orderHeaderDtoToOrderHeader(orderHeaderDto);

		assertNotNull(orderHeader);
		assertEquals(OrderStatus.COMPLETED, orderHeader.getOrderStatus());
		assertEquals(2, orderHeader.getOrderLines().size());
		assertEquals("test order", orderHeader.getName());
		assertEquals(1, orderHeader.getOrderLines().stream().filter(orderLine -> orderLine.getProductId().equals(1L)).findFirst().get().getQuantityOrdered());
		assertEquals(1, orderHeader.getOrderLines().stream().filter(orderLine -> orderLine.getProductId().equals(2L)).findFirst().get().getQuantityOrdered());

		OrderHeaderDto orderHeaderDto2 = orderHeaderMapper.orderHeaderToOrderHeaderDto(orderHeader);
		assertNotNull(orderHeaderDto2);
		assertEquals(OrderStatus.COMPLETED, orderHeaderDto2.getOrderStatus());
		assertEquals(2, orderHeaderDto2.getOrderLines().size());
		assertEquals("test order", orderHeaderDto2.getName());
		assertEquals(1, orderHeaderDto2.getOrderLines().stream().filter(orderLine -> orderLine.getProductId().equals(1L)).findFirst().get().getQuantityOrdered());
		assertEquals(1, orderHeaderDto2.getOrderLines().stream().filter(orderLine -> orderLine.getProductId().equals(2L)).findFirst().get().getQuantityOrdered());

		System.out.println(orderHeaderDto2);

/*		orderLine.setId(1L);
		orderLine.setProductId(1L);
		orderLine.setQuantityOrdered(1);
		OrderLineDto orderLineDto = mapper.orderLineToOrderLineDto(orderLine);
		assertNotNull(orderLineDto);
		assertEquals(orderLine.getId(), orderLineDto.getId());
		assertEquals(orderLine.getProductId(), orderLineDto.getProductId());
		assertEquals(orderLine.getQuantityOrdered(), orderLineDto.getQuantityOrdered());*/
	}
}
