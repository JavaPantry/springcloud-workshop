package com.springcloud.sbsuite.orders.repositories;

import com.springcloud.sbsuite.dto.CustomerDto;
import com.springcloud.sbsuite.dto.OrderStatus;
import com.springcloud.sbsuite.orders.api.NotFoundException;
import com.springcloud.sbsuite.orders.domain.Address;
import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.orders.mappers.CustomerMapper;
import com.springcloud.sbsuite.orders.mappers.CycleAvoidingMappingContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

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
        Set<OrderLine> orderLines = orderHeader.getOrderLines();
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