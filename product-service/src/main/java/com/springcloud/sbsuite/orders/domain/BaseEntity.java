package com.springcloud.sbsuite.orders.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version = 0L;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

	@UpdateTimestamp
	private Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());

	public boolean isNew() {
		return this.id == null;
	}
}
