package com.springcloud.sbsuite.store.api;


import dto.OrderLineDto;
import com.springcloud.sbsuite.store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

//    @Value("${mycloud.config.test.var}")
//    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    @GetMapping()
    public String getOrders() {
        return String.format("Test OrderController (instance %s)", instanceId);
    }

    @GetMapping("/lines")
    public List<OrderLineDto> getOrderLines() {
        List<OrderLineDto> dtos = orderService.fetchOrderLines();
        return dtos;
    }

    @GetMapping("/lines/{id}")
    public OrderLineDto getOrderLine(@PathVariable Long id) {
        OrderLineDto dto = orderService.fetchOrderLineById(id).orElseThrow(NotFoundException::new);
        return dto;
    }

//    @GetMapping("/config-var")
//    public String testConfigVar() {
//        return String.format("Test eureka-client config var > %s", configVar);
//    }

}
