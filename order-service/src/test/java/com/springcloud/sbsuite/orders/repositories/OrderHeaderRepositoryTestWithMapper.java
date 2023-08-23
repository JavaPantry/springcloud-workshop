package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.dto.*;
import com.springcloud.sbsuite.orders.api.NotFoundException;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.orders.mappers.CustomerMapper;
import com.springcloud.sbsuite.orders.mappers.CycleAvoidingMappingContext;
import com.springcloud.sbsuite.orders.mappers.OrderHeaderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderHeaderRepositoryTestWithMapper {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderHeaderMapper orderHeaderMapper;

    @Test
    public void testOrderHeaderRepositoryWithDto() {
//        Customer customer = customerRepository.findById(1L).orElseThrow(() -> new NotFoundException("Customer not found"));

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
                .build();

        orderHeaderDto.setOrderLines(new ArrayList<>() {{
            add(orderLine1);
            add(orderLine2);
        }});


        orderLine1.setOrderHeader(orderHeaderDto);
        orderLine2.setOrderHeader(orderHeaderDto);

        OrderHeader orderHeader = orderHeaderMapper.dtoToEntity(orderHeaderDto);

        OrderHeader orderHeaderSaved = orderHeaderRepository.save(orderHeader);

        assertNotNull(orderHeaderSaved);
        assertNotNull(orderHeaderSaved.getId());
        assertNotNull(orderHeaderSaved.getCreatedDate());
        assertNotNull(orderHeaderSaved.getLastModifiedDate());
        assertEquals("test order", orderHeaderSaved.getName());
        assertEquals(OrderStatus.COMPLETED, orderHeaderSaved.getOrderStatus());
        assertEquals("123 Main St", orderHeaderSaved.getBillToAddress().getStreetAddress());
        assertEquals("Anytown", orderHeaderSaved.getBillToAddress().getCity());
        assertEquals("NY", orderHeaderSaved.getBillToAddress().getState());
        assertEquals("12345", orderHeaderSaved.getBillToAddress().getZipCode());

        assertEquals(2, orderHeaderSaved.getOrderLines().size());
        List<OrderLine> orderLines = orderHeaderSaved.getOrderLines();

        OrderLine orderLine1Saved = orderLines.stream().filter(orderLine -> orderLine.getProductId() == 1L).findFirst().get();
        assertNotNull(orderLine1Saved);
        assertNotNull(orderLine1Saved.getId());
        assertNotNull(orderLine1Saved.getCreatedDate());
        assertNotNull(orderLine1Saved.getLastModifiedDate());
        assertEquals(1, orderLine1Saved.getQuantityOrdered());
        assertEquals(1L, orderLine1Saved.getProductId());
        assertEquals(orderHeaderSaved.getId(), orderLine1Saved.getOrderHeader().getId());

        OrderLine orderLine2Saved = orderLines.stream().filter(orderLine -> orderLine.getProductId() == 2L).findFirst().get();
        assertNotNull(orderLine2Saved);
        assertNotNull(orderLine2Saved.getId());
        assertNotNull(orderLine2Saved.getCreatedDate());
        assertNotNull(orderLine2Saved.getLastModifiedDate());
        assertEquals(1, orderLine2Saved.getQuantityOrdered());
        assertEquals(2L, orderLine2Saved.getProductId());
        assertEquals(orderHeaderSaved.getId(), orderLine2Saved.getOrderHeader().getId());

        System.out.println("Test OrderHeaderRepositoryTest.testOrderHeaderRepositoryWithDto() passed.");
    }


    @Autowired
    CustomerMapper customerMapper;
    @Test
    public void testCustomerRetrieve() {
        assertNotNull(customerRepository);
        Customer customer = customerRepository.findById(1L).orElseThrow(() -> new NotFoundException("Customer not found"));
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertNotNull(customer.getCreatedDate());
        assertNotNull(customer.getLastModifiedDate());
        assertNotNull(customer.getAddress());
        assertNotNull(customer.getContact());
        assertNotNull(customer.getOrders());
        Set<OrderHeader> orders = customer.getOrders();
        assertNotNull(orders);
        assertEquals(2, orders.size());
        OrderHeader orderHeader = orders.stream().findFirst().get();
        assertNotNull(orderHeader);
        assertNotNull(orderHeader.getId());
        assertNotNull(orderHeader.getCreatedDate());
        assertNotNull(orderHeader.getLastModifiedDate());
        assertNotNull(orderHeader.getBillToAddress());
        assertNotNull(orderHeader.getShippingAddress());
        assertNotNull(orderHeader.getOrderLines());
        List<OrderLine> orderLines = orderHeader.getOrderLines();
        OrderLine orderLine = orderLines.stream().findFirst().get();
        assertNotNull(orderLine);
        assertNotNull(orderLine.getId());
        assertNotNull(orderLine.getCreatedDate());
        assertNotNull(orderLine.getLastModifiedDate());
        assertNotNull(orderLine.getOrderHeader());
        assertNotNull(orderLine.getProductId());
        assertNotNull(orderLine.getQuantityOrdered());

        System.out.println("map customr to customerDto");
        CustomerDto customerDto = customerMapper.entityToDto(customer, new CycleAvoidingMappingContext());
        assertNotNull(customerDto);

        System.out.println("Test OrderHeaderRepositoryTest.testCustomerRepository() passed.");
    }
}