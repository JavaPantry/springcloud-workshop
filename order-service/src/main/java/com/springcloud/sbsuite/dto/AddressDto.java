package com.springcloud.sbsuite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddressDto {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
}
