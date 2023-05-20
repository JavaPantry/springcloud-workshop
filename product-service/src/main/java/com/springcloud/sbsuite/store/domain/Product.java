package com.springcloud.sbsuite.store.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Product extends BaseEntity {
	String name;
	String description;
}
