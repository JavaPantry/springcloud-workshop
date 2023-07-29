package com.springcloud.sbsuite.store.api;


import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.dto.ProductDto;
import com.springcloud.sbsuite.dto.ProductsInStoreDto;
import com.springcloud.sbsuite.store.domain.ProductsInStore;
import com.springcloud.sbsuite.store.domain.Store;
import com.springcloud.sbsuite.store.eclient2.InventoryRestService;
import com.springcloud.sbsuite.store.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    private final InventoryRestService inventoryRestService;

    public StoreController(InventoryRestService inventoryRestService) {
        this.inventoryRestService = inventoryRestService;
    }

    @GetMapping()
    public List<Store> getStores() {
        List<Store> dtos = storeService.fetchStores();
        return dtos;
    }

    // TODO check if "/store/inventory" will work
    @GetMapping("/calltoinventory")
    public String testStore() {
        String inventoryResponse = inventoryRestService.getInventory();
        return String.format("Test StoreController (instance %s) hidden call to Inventory service response > %s", instanceId, inventoryResponse);
    }

    @GetMapping("/inventory/{id}")
    public List<ProductsInStoreDto> fetchProductsInStore(@PathVariable Long id) {
        List<ProductsInStoreDto> dtos = new ArrayList<>();
        List<ProductsInStore> products = storeService.fetchProductsInStore(id);

        List<InventoryDto> productsInStore =  inventoryRestService.getInventoryInStore(id);
        HashMap<Long, Integer> productInventoryMap = new HashMap<>();
        for (InventoryDto product : productsInStore) {
            productInventoryMap.put(product.getProductId(), product.getQuantity());
        }

        for (ProductsInStore product : products) {
            Integer inventory = productInventoryMap.get(product.getProductId());
            if (inventory == null) {
                inventory = 0;
            }
            ProductsInStoreDto dto = ProductsInStoreDto.builder()
                    .id(product.getId())
                    .productId(product.getProductId())
                    .name("Fake name for now")
                    .description("Fake description for now")
                    .quantity(inventory)
                    .price(product.getPrice())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}
