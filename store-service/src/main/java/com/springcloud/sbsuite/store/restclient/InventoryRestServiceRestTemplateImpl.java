package com.springcloud.sbsuite.store.restclient;

import com.springcloud.sbsuite.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventoryRestServiceRestTemplateImpl implements InventoryRestService {

	@Value("${hostnameurl}")
	private String hostnameurl;

	private final RestTemplate restTemplate;

	public InventoryRestServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication("avp", "password")
												.build();
	}
	@Override
	public String getInventory() {
		String storeInventoryUrl = String.format("http://%s:8765/inventory", hostnameurl);

		String response = restTemplate.exchange(storeInventoryUrl, HttpMethod.GET, null, String.class).getBody();
		return String.format("> InventoryRestServiceRestTemplateImpl call to Inventory Service > %s", response);
	}

	@Override
	public List<InventoryDto> getInventoryInStore(Long storeId) {
		String storeInventoryUrl = String.format("http://%s:8765/inventory/store/%s", hostnameurl, storeId);

		// - This list will contain a list of HashMaps, where each HashMap is a InventoryDto
		// and cause - - java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.springcloud.sbsuite.dto.InventoryDto
		/*List<InventoryDto> storeInventory  = restTemplate.exchange(storeInventoryUrl,
												HttpMethod.GET, null,
												List.class).getBody();*/

		ResponseEntity<List<InventoryDto>> responseEntity = restTemplate.exchange(storeInventoryUrl, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<InventoryDto>>() {});

		List<InventoryDto> storeInventory = responseEntity.getBody();
		return storeInventory;
	}


}
