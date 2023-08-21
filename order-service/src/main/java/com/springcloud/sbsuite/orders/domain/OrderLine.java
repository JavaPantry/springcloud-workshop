package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    //AVP August 17 @14:30 add (fetch = FetchType.LAZY)
    //AVP August 21 @19:00 , @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST) cause error
    @ToString.Exclude
    // Cause recursive header creation cascade = CascadeType.MERGE,  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "order_header_id") // Specifies the column name for the foreign key
    @EqualsAndHashCode.Exclude // Exclude this field from hash code calculation
    private OrderHeader orderHeader;

    private Long productId;

}
