package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Embedded;

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

    //(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderHeader> orders = new LinkedHashSet<>();

//    public void addOrderHeader(OrderHeader orderHeader) {
//        if (orders == null) {
//            orders = new LinkedHashSet<>();
//        }
//        orders.add(orderHeader);
//    }
}
