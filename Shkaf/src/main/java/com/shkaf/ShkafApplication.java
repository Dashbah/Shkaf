package com.shkaf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@OpenAPIDefinition
public class ShkafApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShkafApplication.class);
		ConfigurableApplicationContext context = app.run(args);
		System.out.println("Application is running on port: " + context.getEnvironment().getProperty("server.port"));
//		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
//		System.out.println(encoder.encode("pass"));
	}
}
