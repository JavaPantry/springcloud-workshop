package com.springcloud.sbsuite.store.restclient;

import com.springcloud.sbsuite.dto.InventoryDto;
import com.springcloud.sbsuite.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductRestServiceImpl implements ProductRestService{

	@Value("${hostnameurl}")
	private String hostnameurl;

	private final RestTemplate restTemplate;

	public ProductRestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication("avp", "password")
				.build();
	}
	@Override
	public List<ProductDto> getProductsInStore(List<Long> ids) {
		String idsStr = ids.toString().replace("[", "").replace("]", "");
		String storeInventoryUrl = String.format("http://%s:8765/product/select/%s", hostnameurl, idsStr);


		ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(storeInventoryUrl, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<ProductDto>>(){} );

		List<ProductDto> products = responseEntity.getBody();

		return products;
	}
}
