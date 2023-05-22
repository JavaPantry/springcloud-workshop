package com.springcloud.sbsuite.prototype.api;


import com.springcloud.sbsuite.prototype.eclient2.Eclient2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prototype")
public class PrototypeController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    public PrototypeController(){
            }


    // TODO check if "/store/inventory" will work
    @GetMapping("/")
    public String testStore() {
        return "Hello from Prototype Service " + instanceId + "!";
    }

}
