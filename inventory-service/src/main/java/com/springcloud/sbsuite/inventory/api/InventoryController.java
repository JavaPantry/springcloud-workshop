package com.springcloud.sbsuite.inventory.api;


import com.springcloud.sbsuite.inventory.eclient2.Eclient2Service;
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

    private final Eclient2Service eclient2Service;

    public InventoryController(Eclient2Service eclient2Service) {
        this.eclient2Service = eclient2Service;
    }


    @GetMapping("/test")
    public String test() {
        // return text with instance id
        return String.format("Test InventoryController (instance %s) > Hello", instanceId);
    }

    // TODO check if "/store/inventory" will work
    @GetMapping("/storeinventory")
    public String testStore() {
        String eclient2response = eclient2Service.getInventory();
        return String.format("Test eureka-client (instance %s) eclient2 response > %s", instanceId, eclient2response);
    }

    @GetMapping("/config-var")
    public String testConfigVar() {
        return String.format("Test eureka-client config var > %s", configVar);
    }
}
