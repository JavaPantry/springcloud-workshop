package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.dto.AddressDto;
import com.springcloud.sbsuite.dto.OrderHeaderDto;
import com.springcloud.sbsuite.dto.OrderLineDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.api.NotFoundException;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderHeaderRepositoryTest {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Test
    public void testOrderHeaderRepository() {
        assertNotNull(orderHeaderRepository);
        assertNotNull(customerRepository);

//        Customer customer = customerRepository.findById(1L).orElseThrow(() -> new NotFoundException("Customer not found"));

        Address fakeAddress = Address.builder()
                .streetAddress("123 Main St")
                .city("Anytown")
                .state("NY")
                .zipCode("12345").build();

        OrderLine orderLine1 = OrderLine.builder().quantityOrdered(1).productId(1L).build();
        OrderLine orderLine2 = OrderLine.builder().quantityOrdered(1).productId(2L).build();

        OrderHeader orderHeader = OrderHeader.builder()
                .name("test order")
//                .customer(customer)
//                .orderLines(new HashSet<OrderLine>() {{
//                    add(orderLine1);
//                    add(orderLine2);
//                }})
                .billToAddress(fakeAddress)
                .shippingAddress(fakeAddress)
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        orderHeader.setOrderLines(new HashSet<OrderLine>() {{
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