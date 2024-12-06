package com.realtime.ticketing.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketingSystemBackendApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TicketingSystemBackendApplication.class);
		app.run(args);

		System.exit(0);
	}
}
