package com.springcloud.sbsuite.orders.domain;

import com.springcloud.sbsuite.dto.OrderStatus;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "shippingAddress.streetAddress",
                column = @Column(name = "shipping_address")
        ),
        @AttributeOverride(
                name = "shippingAddress.city",
                column = @Column(name = "shipping_city")
        ),
        @AttributeOverride(
                name = "shippingAddress.state",
                column = @Column(name = "shipping_state")
        ),
        @AttributeOverride(
                name = "shippingAddress.zipCode",
                column = @Column(name = "shipping_zip_code")
        ),
        @AttributeOverride(
                name = "billToAddress.streetAddress",
                column = @Column(name = "bill_to_address")
        ),
        @AttributeOverride(
                name = "billToAddress.city",
                column = @Column(name = "bill_to_city")
        ),
        @AttributeOverride(
                name = "billToAddress.state",
                column = @Column(name = "bill_to_state")
        ),
        @AttributeOverride(
                name = "billToAddress.zipCode",
                column = @Column(name = "bill_to_zip_code")
        )
})
public class OrderHeader extends BaseEntity {

    private String name;
    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //AVP August 17 @14:30 replace fetch = FetchType.EAGER with fetch = FetchType.LAZY)
    //(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // Exclude this field from hash code calculation
    private Set<OrderLine> orderLines = new HashSet<OrderLine>();

    /*@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "orderHeader")
    private OrderApproval orderApproval;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }
    */

    /*public OrderHeader(Customer customer, Address shippingAddress, Address billToAddress) {
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billToAddress = billToAddress;
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }
        orderLines.add(orderLine);
        //orderLine.setOrderHeader(this);
    }*/
}
