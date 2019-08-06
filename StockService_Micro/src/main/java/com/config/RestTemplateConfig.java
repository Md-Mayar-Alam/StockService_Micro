package com.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient
public class RestTemplateConfig {

	/*@LoadBalanced*/
	@Bean
	public RestTemplate restTemplate() {
		System.out.println("RestTemplate Created");
		return new RestTemplate();
	}
}
