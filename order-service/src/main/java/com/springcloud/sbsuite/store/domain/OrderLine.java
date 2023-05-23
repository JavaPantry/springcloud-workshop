package com.springcloud.sbsuite.store.domain;

import com.springcloud.sbsuite.store.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    //@ManyToOne
    //private ProductDto product;

    private Long productId;

    public OrderLine(Integer quantityOrdered, OrderHeader orderHeader, Long productId) {
        this.quantityOrdered = quantityOrdered;
        this.orderHeader = orderHeader;
        this.productId = productId;
    }

}
