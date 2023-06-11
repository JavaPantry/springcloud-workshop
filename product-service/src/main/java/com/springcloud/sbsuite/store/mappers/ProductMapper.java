package com.springcloud.sbsuite.store.mappers;

import com.springcloud.sbsuite.store.domain.Product;
import com.springcloud.sbsuite.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ProductMapper {
	Product productDtoToProduct(ProductDto productDto);
	ProductDto productToProductDto(Product product);

}
