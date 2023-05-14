package com.springcloud.sbsuite.eurekaclient2.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class TestController {
    @GetMapping("/name")
    public String test() {
        return "Test eureka-client 2  > Hello";
    }
}
