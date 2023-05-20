package com.springcloud.sbsuite.store.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@Embeddable
public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}
