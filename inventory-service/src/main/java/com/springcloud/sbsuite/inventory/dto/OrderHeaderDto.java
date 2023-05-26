package com.springcloud.sbsuite.inventory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@ToString(callSuper = true)
@Builder
public class OrderHeaderDto{

    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("version")
    private Integer version = null;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate = null;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("lastModifiedDate")
    private OffsetDateTime lastModifiedDate = null;

    private String name;
    //private Customer customer;
    private AddressDto shippingAddress;
    private AddressDto billToAddress;
    private OrderStatus orderStatus;
    private Set<OrderLineDto> orderLines;
}
