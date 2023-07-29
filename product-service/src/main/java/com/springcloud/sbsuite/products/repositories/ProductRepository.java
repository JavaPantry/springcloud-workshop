package com.springcloud.sbsuite.products.repositories;

import com.springcloud.sbsuite.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jt on 12/11/21.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);

	/*@Query("SELECT e FROM Product e WHERE e.id IN :ids")
	List<Product> findByArrayOfIds(@Param("arrayOfField1Values") List<Long> ids);*/

    List<Product> findByIdIn(List<Long> ids);
}
