package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Contact {
	private String name;
	private String phone;
	private String email;
}
