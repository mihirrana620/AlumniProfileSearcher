package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.assignment.*")
@EnableJpaRepositories(basePackages = "com.assignment.dao")
@EntityScan(basePackages = "com.assignment.entity")
public class AlumniProfileSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumniProfileSearcherApplication.class, args);
	}

}