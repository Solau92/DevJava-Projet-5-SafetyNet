package com.safetynet.safetynetalerts.configuration;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorHttpExchangesConfiguration {
	
	/**
	 * Makes the HttpExchanges endpoint work in Spring Boot 3
	 * @return a InMemoryHttpExchangeRepository
	 */
	@Bean
	public HttpExchangeRepository httpTraceRepository() {
		return new InMemoryHttpExchangeRepository();
	}
	
	

}
