package com.springcloud.sbsuite.store.domain;

import com.springcloud.sbsuite.store.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    private Long productId;

    public OrderLine(Integer quantityOrdered, OrderHeader orderHeader, Long productId) {
        this.quantityOrdered = quantityOrdered;
        this.orderHeader = orderHeader;
        this.productId = productId;
    }

}
