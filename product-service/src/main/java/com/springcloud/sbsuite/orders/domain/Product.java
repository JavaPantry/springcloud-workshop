package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@NotBlank
	@NotNull
	@Size(min = 2, max = 100)
	@Column(length = 100)
	String name;
	@NotBlank
	@NotNull
	@Size(min = 2, max = 100)
	@Column(length = 100)
	String description;
}
