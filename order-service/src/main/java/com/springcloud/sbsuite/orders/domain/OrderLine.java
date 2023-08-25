package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // Exclude this field from hash code calculation
    private OrderHeader orderHeader;

    private Long productId;

}
