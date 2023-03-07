package com.safetynet.saftynetalerts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class LombokLoggingController {
	
	@RequestMapping("/")
	public String index() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		
		return "hey !";
		
	}

}
