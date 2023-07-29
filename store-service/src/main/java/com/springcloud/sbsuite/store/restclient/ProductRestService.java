package com.springcloud.sbsuite.store.restclient;

import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.dto.ProductDto;

import java.util.List;

public interface ProductRestService {
	List<ProductDto> getProductsInStore(List<Long> ids);
}
