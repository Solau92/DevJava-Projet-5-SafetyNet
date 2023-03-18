package com.safetynet.safetynetalerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Acts as a main class for Spring Boot application Implements
 * CommandLineRunner, so the method run is executed before the application
 * starts but after all beans are created
 */
@SpringBootApplication
public class SafetyNetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	/**
	 * Method executed after the application context is loaded and before the Spring
	 * Application main method is completed
	 */
	@Override
	public void run(String... args) throws Exception {

	}

}
