package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  
public class TestProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(TestProject3Application.class, args);
	}

}
