package com.springcloud.sbsuite.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * this isn't a one-to-one mapping of the ProductsInStore entity, but integrated view of the product in store
 * which consists of the product info, quantity and price
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsInStoreDto {
	private Long        id = null;

	private Long        productId;
	private String      name;
	private String      description;

	private Integer     quantity = 0;
	private BigDecimal  price;
}
