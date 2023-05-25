package com.springcloud.sbsuite.store.api;


import com.springcloud.sbsuite.store.domain.Product;
import com.springcloud.sbsuite.store.dto.ProductDto;
import com.springcloud.sbsuite.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

//    @Value("${mycloud.config.test.var}")
//    private String configVar;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<ProductDto> getProducts() {
        List<ProductDto> dtos = productService.fetchProducts();
        return dtos;
    }

    @GetMapping(value = "/{id}")
    public ProductDto getProductById(Long id) {
        ProductDto dto = productService.fetchProductById(id).orElseThrow(NotFoundException::new);
        return dto;
    }

//    @GetMapping("/config-var")
//    public String testConfigVar() {
//        return String.format("Test eureka-client config var > %s", configVar);
//    }

}
