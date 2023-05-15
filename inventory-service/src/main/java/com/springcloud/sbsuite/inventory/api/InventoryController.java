package com.springcloud.sbsuite.inventory.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Value("${mycloud.config.test.var}")
    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    @GetMapping("/")
    public String getInventory() {
        return "InventoryController serve \"/inventory\"  [\"Part 1\", \"Part 2\", \"Part 3\", \"Part 4\", \"Part 5\"]  ";
    }


    @GetMapping("/test")
    public String test() {
        // return text with instance id
        return String.format("Test InventoryController (instance %s) > Hello", instanceId);
    }


    @GetMapping("/config-var")
    public String testConfigVar() {
        return String.format("Test eureka-client config var > %s", configVar);
    }
}
