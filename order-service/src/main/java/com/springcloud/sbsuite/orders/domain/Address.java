package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}
