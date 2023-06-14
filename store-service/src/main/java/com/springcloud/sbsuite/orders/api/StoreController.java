package com.springcloud.sbsuite.orders.api;


import com.springcloud.sbsuite.orders.eclient2.Eclient2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    private final Eclient2Service eclient2Service;

    public StoreController(Eclient2Service eclient2Service) {
        this.eclient2Service = eclient2Service;
    }


    // TODO check if "/store/inventory" will work
    @GetMapping("/inventory")
    public String testStore() {
        String inventoryResponse = eclient2Service.getInventory();
        return String.format("Test StoreController (instance %s) hidden call to Inventory service response > %s", instanceId, inventoryResponse);
    }

}
