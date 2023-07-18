package com.springcloud.sbsuite.store.eclient2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
}
