package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.dto.*;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderHeaderRepositoryTest {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    @Transactional
    public void testOrderHeaderRepository() {
        assertNotNull(orderHeaderRepository);

        Address fakeAddress = Address.builder()
                .streetAddress("123 Main St")
                .city("Anytown")
                .state("NY")
                .zipCode("12345").build();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantityOrdered(1);orderLine1.setProductId(1L);
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantityOrdered(1);orderLine2.setProductId(2L);

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setName("test order");
        orderHeader.setBillToAddress(fakeAddress);
        orderHeader.setShippingAddress(fakeAddress);
        orderHeader.setOrderStatus(OrderStatus.COMPLETED);
        orderHeader.setOrderLines(new HashSet<>() {{
            add(orderLine1);
            add(orderLine2);
        }});


        orderLine1.setOrderHeader(orderHeader);
        orderLine2.setOrderHeader(orderHeader);

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
        Set<OrderLine> orderLines = orderHeaderSaved.getOrderLines();

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

        System.out.println("Test OrderHeaderRepositoryTest.testOrderHeaderRepository() passed.");
    }

}