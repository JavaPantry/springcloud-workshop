package com.springcloud.sbsuite.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 - adding `@EnableEurekaClient` **NOT RECOGNIZED**
 - [EnableEurekaClient import doesn't exist](https://stackoverflow.com/questions/68285299/enableeurekaclient-import-doesnt-exist)
    - @EnableEurekaClient is deprecated no need to annotate at springboot main applicationit is fine
      if we add the `spring-cloud-starter-netflix-eureka-client` dependency in `pom`
      and if we have the `spring.application.name=eclient` in yml or properties file it will be registered to Eureka Server
 */
@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

}
