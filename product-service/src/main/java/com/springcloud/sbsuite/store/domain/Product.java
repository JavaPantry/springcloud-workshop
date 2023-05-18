package com.springcloud.sbsuite.store.domain;

import com.springcloud.sbsuite.shareddata.base.BaseEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
public class Product extends BaseEntity {
	String name;
	String description;
}
