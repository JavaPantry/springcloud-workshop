package com.springcloud.sbsuite.store.api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

//    @Value("${mycloud.config.test.var}")
//    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    @GetMapping("/")
    public String getOrders() {
        return String.format("Test OrderController (instance %s)", instanceId);
    }

//    @GetMapping("/config-var")
//    public String testConfigVar() {
//        return String.format("Test eureka-client config var > %s", configVar);
//    }

}
