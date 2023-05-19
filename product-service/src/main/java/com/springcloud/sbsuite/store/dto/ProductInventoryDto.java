package com.springcloud.sbsuite.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInventoryDto {
    private Long id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private Long beerId;
    private String upc;
    private Integer quantityOnHand;
}
