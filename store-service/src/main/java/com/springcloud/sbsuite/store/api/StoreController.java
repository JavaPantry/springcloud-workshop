package com.springcloud.sbsuite.store.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    @GetMapping("/inventory")
    public String getInventory() {
        return "StoreController call /inventory  > Hello > ";
    }
}
