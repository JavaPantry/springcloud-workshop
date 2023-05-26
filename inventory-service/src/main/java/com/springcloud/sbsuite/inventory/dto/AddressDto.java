package com.springcloud.sbsuite.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}
