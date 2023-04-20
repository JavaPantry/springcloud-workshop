package com.springcloud.sbsuite.eurekaclient.eclient2;

import org.springframework.stereotype.Component;

@Component
public class Eclient2ServiceRestTemplateImpl implements Eclient2Service{
	@Override
	public String testName() {
		return "Test eureka-client 2  > Hello";
	}
}
