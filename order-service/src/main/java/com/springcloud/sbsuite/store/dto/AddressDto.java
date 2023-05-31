package com.springcloud.sbsuite.store.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}
