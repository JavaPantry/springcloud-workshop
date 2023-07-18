package com.springcloud.sbsuite.store.api;


import com.springcloud.sbsuite.store.domain.ProductsInStore;
import com.springcloud.sbsuite.store.eclient2.Eclient2Service;
import com.springcloud.sbsuite.store.repositories.StoreRepository;
import com.springcloud.sbsuite.store.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

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

    @GetMapping("/inventory/{id}")
    public List<ProductsInStore> fetchProductsInStore(@PathVariable Long id) {
        List<ProductsInStore> products = storeService.fetchProductsInStore(id);
        return products;
    }
}
