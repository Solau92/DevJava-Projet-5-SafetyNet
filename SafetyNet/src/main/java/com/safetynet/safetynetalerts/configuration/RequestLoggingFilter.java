package com.safetynet.safetynetalerts.configuration;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Created to configure loggers at info level (default value is debug level)
 *
 */
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		logger.info(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		logger.info(message);
	}

}
