package com.safetynet.safetynetalerts.configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseLoggingFilter extends OncePerRequestFilter {

	   private final static Logger log = LoggerFactory.getLogger(ResponseLoggingFilter.class);

	   private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
	      MediaType.valueOf("text/*"),
	      MediaType.APPLICATION_FORM_URLENCODED,
	      MediaType.APPLICATION_JSON,
	      MediaType.APPLICATION_XML,
	      MediaType.valueOf("application/*+json"),
	      MediaType.valueOf("application/*+xml"),
	      MediaType.MULTIPART_FORM_DATA
	   );

	   private static final List<String> SENSITIVE_HEADERS = Arrays.asList(
	     "authorization",
	      "proxy-authorization"
	   );
	   
	   private boolean enabled = true;
	   
	   @ManagedOperation(description = "Enable logging of HTTP requests and responses")
	   public void enable() {
	      this.enabled = true;
	   }

	   @ManagedOperation(description = "Disable logging of HTTP requests and responses")
	   public void disable() {
	      this.enabled = false;
	   }

	   @Override
	   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	      if (isAsyncDispatch(request)) {
	         filterChain.doFilter(request, response);
	      } else {
	         doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
	      }
	   }

	   protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {

	      StringBuilder msg = new StringBuilder();

	      try {
	         filterChain.doFilter(request, response);
	      }
	      finally {
	         afterRequest(request, response, msg);
	         if(log.isInfoEnabled()) {      	 
	        	if(response.getStatus() >=400) {
		            log.error(msg.toString());
	        	} else {
		            log.info(msg.toString());
	        	}        	 
	         }
	         response.copyBodyToResponse();
	      }
	   }

	   protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, StringBuilder msg) {
	      if (enabled && log.isInfoEnabled()) {
	         msg.append("Response [ status : ");	         
	         logResponse(response, "",  msg);      
	      }
	   }

	   private static void logResponse(ContentCachingResponseWrapper response, String prefix, StringBuilder msg) {
	      int status = response.getStatus();
	      msg.append(String.format("%s %s %s", prefix, status, HttpStatus.valueOf(status).getReasonPhrase())).append("");
	      response.getHeaderNames()
	              .forEach(headerName ->
	                          response.getHeaders(headerName)
	                                  .forEach(headerValue ->
	                                  {
	                                     if(isSensitiveHeader(headerName)) {
	                                        msg.append(String.format("%s %s: %s", prefix, headerName, "*******")).append("");
	                                     }
	                                     else {
	                                        msg.append(String.format("%s %s: %s", prefix, headerName, headerValue)).append("");
	                                     }
	                                  }));
	      byte[] content = response.getContentAsByteArray();
	      if (content.length > 0) {
	         logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix, msg);
	      }
	   }

	   private static void logContent(byte[] content, String contentType, String contentEncoding, String prefix, StringBuilder msg) {
	      MediaType mediaType = MediaType.valueOf(contentType);
	      boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
	      if (visible) {
	         try {
	            String contentString = new String(content, contentEncoding);
	            Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> msg.append(prefix + ",\n ").append("body : ").append(line).append("]\n"));
	         } catch (UnsupportedEncodingException e) {
	            msg.append(String.format("%s [%d bytes content]", prefix, content.length)).append("\n");
	         }
	      } else {
	         msg.append(String.format("%s [%d bytes content]", prefix, content.length)).append("\n");
	      }
	   }

	   private static boolean isSensitiveHeader(String headerName) {
	      return SENSITIVE_HEADERS.contains(headerName.toLowerCase());
	   }

	   private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
	      if (request instanceof ContentCachingRequestWrapper) {
	         return (ContentCachingRequestWrapper) request;
	      } else {
	         return new ContentCachingRequestWrapper(request);
	      }
	   }

	   private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
	      if (response instanceof ContentCachingResponseWrapper) {
	         return (ContentCachingResponseWrapper) response;
	      } else {
	         return new ContentCachingResponseWrapper(response);
	      }
	   }

}
