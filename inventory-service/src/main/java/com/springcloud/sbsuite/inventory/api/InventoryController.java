package com.springcloud.sbsuite.inventory.api;

import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

//    @Value("${mycloud.config.test.var}")
//    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    InventoryService inventoryService;

    @GetMapping()
    public List<InventoryDto> getInventory() {
        List<InventoryDto> dtos = inventoryService.fetchInventories();
        return dtos;
    }

    @GetMapping(value = "/{id}")
    public InventoryDto getInventoryById(@PathVariable Long id) {
        InventoryDto dto = inventoryService.fetchInventoryById(id).orElseThrow(NotFoundException::new);
        return dto;
    }

    @GetMapping(value = "/product/{id}")
    public InventoryDto getInventoryByProductId(@PathVariable Long id) {
        InventoryDto dto = inventoryService.fetchInventoryByProductId(id).orElseThrow(NotFoundException::new);
        return dto;
    }

    @GetMapping("/config-var")
    public String testConfigVar() {
        //return String.format("Test eureka-client config var > %s", configVar);
        return String.format("Test eureka-client config var > Can't read configVar");
    }
}
