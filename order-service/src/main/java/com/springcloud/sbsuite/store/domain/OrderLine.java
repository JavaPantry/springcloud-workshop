package com.springcloud.sbsuite.store.domain;

import com.springcloud.sbsuite.store.dto.ProductDto;
//import jakarta.persistence.Entity;
//import jakarta.persistence.ManyToOne;

//@Entity
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    //@ManyToOne
    private OrderHeader orderHeader;

    //@ManyToOne
    private ProductDto product;

    public OrderLine(Integer quantityOrdered, OrderHeader orderHeader, ProductDto product) {
        this.quantityOrdered = quantityOrdered;
        this.orderHeader = orderHeader;
        this.product = product;
    }

}
