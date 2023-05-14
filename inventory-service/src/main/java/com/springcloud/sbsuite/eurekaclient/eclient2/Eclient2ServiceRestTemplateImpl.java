package com.springcloud.sbsuite.eurekaclient.eclient2;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Eclient2ServiceRestTemplateImpl implements Eclient2Service{

	private final RestTemplate restTemplate;

	public Eclient2ServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication("avp", "password")
												.build();
	}
	@Override
	public String testName() {
		String eclient2url = "http://localhost:8765/new/name";
		String response = restTemplate.exchange(eclient2url, HttpMethod.GET, null, String.class).getBody();
		return String.format("> eureka-client 2 Service > %s", response);
	}
}
