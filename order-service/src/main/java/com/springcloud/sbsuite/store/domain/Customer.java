package com.springcloud.sbsuite.store.domain;

import jakarta.persistence.Embedded;
import lombok.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@Entity
public class Customer extends BaseEntity {

    @Embedded
    private Address address;
    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<OrderHeader> orders = new LinkedHashSet<>();

//    public void addOrderHeader(OrderHeader orderHeader) {
//        if (orders == null) {
//            orders = new LinkedHashSet<>();
//        }
//        orders.add(orderHeader);
//    }
}
