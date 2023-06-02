package com.springcloud.sbsuite.store.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	String name;
	@NotBlank
	@NotNull
	String description;
}
