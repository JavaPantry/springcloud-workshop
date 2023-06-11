package com.springcloud.sbsuite.store.services;

import com.springcloud.sbsuite.store.domain.Product;
import com.springcloud.sbsuite.dto.ProductDto;
import com.springcloud.sbsuite.store.mappers.ProductMapper;
import com.springcloud.sbsuite.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductMapper productMapper;

	@Override
	public List<ProductDto> fetchProducts(){
		List<ProductDto> products = productRepository.findAll()
													.stream()
													.map(productMapper::productToProductDto)
													.collect(Collectors.toList());
		return products;
	}

	@Override
	public Optional<ProductDto> fetchProductById(Long id) {
		return Optional.ofNullable(productMapper.productToProductDto(
																	productRepository.findById(id).orElse(null)
																	)
		);
	}

	@Override
	public Optional<ProductDto> saveProduct(ProductDto dto) {
		Product product = null;
		if(dto.getId() != null) {
			product = productRepository.findById(dto.getId()).orElse(null);
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
		}else {
			product = productMapper.productDtoToProduct(dto);
		}
		Product updated = productRepository.save(product);
		return Optional.of(productMapper.productToProductDto(updated) );
	}

	@Override
	public boolean deleteProduct(ProductDto dto) {
		if (productRepository.existsById(dto.getId())) {
			productRepository.deleteById(dto.getId());
			return true;
		}
		return false;
	}


}
