package com.springcloud.sbsuite.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDto {
	private String name;
	private String phone;
	private String email;
}
