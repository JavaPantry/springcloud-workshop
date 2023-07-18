package com.springcloud.sbsuite.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@Entity
public class ProductsInStore extends BaseEntity {
    @ManyToOne
    private Store store;

    @NotBlank
    @NotNull
    private Long productId;

    @NotBlank
    @NotNull
    @Column(precision=10, scale=2)
    private BigDecimal price;
}
