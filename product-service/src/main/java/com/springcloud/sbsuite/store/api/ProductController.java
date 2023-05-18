package com.springcloud.sbsuite.store.api;


import com.springcloud.sbsuite.store.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

//    @Value("${mycloud.config.test.var}")
//    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    @GetMapping("/")
    public String getProducts() {
        Product product = Product.builder().name("Test Product").description("Test Product Description").build();
        return String.format("Test ProductController (instance %s) Product: %s", instanceId, product.toString());
    }

//    @GetMapping("/config-var")
//    public String testConfigVar() {
//        return String.format("Test eureka-client config var > %s", configVar);
//    }

}
