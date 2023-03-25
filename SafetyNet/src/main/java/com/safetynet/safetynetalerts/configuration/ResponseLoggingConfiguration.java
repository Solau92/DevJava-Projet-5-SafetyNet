package com.safetynet.safetynetalerts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseLoggingConfiguration {
	
	@Bean
	public ResponseLoggingFilter responseLoggingFilter() {
	       return new ResponseLoggingFilter();
	   }
}
