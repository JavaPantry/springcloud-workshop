package com.springcloud.sbsuite.orders.mappers;

import com.springcloud.sbsuite.orders.domain.Product;
import com.springcloud.sbsuite.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ProductMapper {
	Product productDtoToProduct(ProductDto productDto);
	ProductDto productToProductDto(Product product);

}
