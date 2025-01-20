package com.example.SpringFinalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringFinalProjectApplication {

	public static void main(String[] args) {

		System.out.println("Hello World");

		SpringApplication.run(SpringFinalProjectApplication.class, args);
	}

}
