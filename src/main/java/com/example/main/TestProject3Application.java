package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info (
				title = "Spring boot CRUD.",
				description = "Spring boot CRUD Doumentation.",
				version = "v1.0",
				contact = @Contact (
						name = "Test",
						email = "xyz@gmail.com"),
				license = @License (
						name = "TempLicense")))
@SpringBootApplication
@EnableCaching  
public class TestProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(TestProject3Application.class, args);
	}

}
