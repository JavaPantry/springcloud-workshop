package com.springcloud.sbsuite.store.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@Entity
public class Product extends BaseEntity {
	String name;
	String description;
}
