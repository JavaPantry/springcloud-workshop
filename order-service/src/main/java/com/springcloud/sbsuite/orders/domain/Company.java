package com.springcloud.sbsuite.orders.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Company extends BaseEntity {
	String code;
	String name;
}
