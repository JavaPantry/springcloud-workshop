package com.springcloud.sbsuite.products.api;


import com.springcloud.sbsuite.dto.ProductDto;
import com.springcloud.sbsuite.products.domain.Product;
import com.springcloud.sbsuite.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public List<ProductDto> getProducts() {
        List<ProductDto> dtos = productService.fetchProducts();
        return dtos;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@Validated @RequestBody ProductDto productDto) {
        ProductDto dto = productService.saveProduct(productDto).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        ProductDto dto = productService.fetchProductById(id).orElseThrow(NotFoundException::new);
        return dto;
    }

    @GetMapping("/select/{ids}")
    public List<ProductDto> getProductsByIds(@PathVariable List<Long> ids) {
        return productService.fetchProductsByIds(ids);
    }

//    @GetMapping("/config-var")
//    public String testConfigVar() {
//        return String.format("Test eureka-client config var > %s", configVar);
//    }

}
