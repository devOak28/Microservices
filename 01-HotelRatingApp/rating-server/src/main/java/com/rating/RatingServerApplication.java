package com.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServerApplication.class, args);
	}

}
