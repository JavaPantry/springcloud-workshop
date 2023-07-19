package com.springcloud.sbsuite.apigateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class AppCorsConfiguration {

	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*"); // Set the allowed origin, you can change this to a specific origin if needed.
		corsConfig.addAllowedHeader("*"); // Set the allowed headers, you can customize this according to your requirements.
		corsConfig.addAllowedMethod("*"); // Set the allowed HTTP methods, you can customize this too.

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig); // Apply the CORS configuration to all paths.

		return new CorsWebFilter(source);
	}
}
