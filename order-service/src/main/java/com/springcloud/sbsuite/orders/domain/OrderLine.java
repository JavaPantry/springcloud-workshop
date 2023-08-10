package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "order_header_id") // Specifies the column name for the foreign key
    @EqualsAndHashCode.Exclude // Exclude this field from hash code calculation
    private OrderHeader orderHeader;

    private Long productId;

}
