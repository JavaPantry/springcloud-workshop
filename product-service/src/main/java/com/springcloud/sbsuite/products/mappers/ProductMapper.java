package com.springcloud.sbsuite.products.mappers;

import com.springcloud.sbsuite.products.domain.Product;
import com.springcloud.sbsuite.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ProductMapper {
	Product productDtoToProduct(ProductDto productDto);
	ProductDto productToProductDto(Product product);

}
