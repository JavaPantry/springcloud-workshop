package com.springcloud.sbsuite.store.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDto {
	private String name;
	private String phone;
	private String email;
}
