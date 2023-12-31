/*
 *  Copyright 2019 the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.springcloud.sbsuite.inventory.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jt on 2019-01-26.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@Entity
public class Inventory extends BaseEntity {

    @Builder
    public Inventory(Long productId, Long storeId, Integer quantity) {
        this.productId = productId;
        this.storeId = storeId;
        this.quantity = quantity;
    }

    private Long productId;
    private Long storeId;
    private Integer quantity = 0;
}
