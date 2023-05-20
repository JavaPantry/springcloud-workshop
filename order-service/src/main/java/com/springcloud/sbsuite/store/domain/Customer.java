package com.springcloud.sbsuite.store.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    //@Embedded
    private Address address;
    //@Embedded
    private Contact contact;
    private String phone;
    private String email;

    //@OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<OrderHeader> orders = new LinkedHashSet<>();

    public void addOrderHeader(OrderHeader orderHeader) {
        if (orders == null) {
            orders = new LinkedHashSet<>();
        }
        orders.add(orderHeader);
    }
}
