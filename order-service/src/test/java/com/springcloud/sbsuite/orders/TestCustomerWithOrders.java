package com.springcloud.sbsuite.orders;

import com.springcloud.sbsuite.orders.domain.Customer;
import com.springcloud.sbsuite.orders.domain.OrderHeader;
import com.springcloud.sbsuite.orders.domain.OrderLine;
import com.springcloud.sbsuite.orders.repositories.CustomerRepository;
import com.springcloud.sbsuite.orders.repositories.OrderHeaderRepository;
import com.springcloud.sbsuite.orders.repositories.OrderLineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @ComponentScan(basePackages = {"com.sbsuite.sbsuitedata.bootstrap"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestCustomerWithOrders {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	@Autowired
	OrderLineRepository orderLineRepository;

	@Test
	void testFetchAll() {
		List<Customer> customers = customerRepository.findAll();
		assertNotNull(customers);
		Customer customer = customers.get(0);
		assertNotNull(customer);
		Set<OrderHeader> orderHeaders = customer.getOrders();
		assertNotNull(orderHeaders);
		OrderHeader orderHeader = (OrderHeader)orderHeaders.toArray()[0];
		assertNotNull(orderHeader);
		Set<OrderLine> orderLines = orderHeader.getOrderLines();
		OrderLine orderLine = (OrderLine)orderLines.toArray()[0];
		assertNotNull(orderLine);
	}
}
