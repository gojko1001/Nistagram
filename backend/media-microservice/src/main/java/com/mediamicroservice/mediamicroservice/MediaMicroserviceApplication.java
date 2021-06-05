package com.mediamicroservice.mediamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MediaMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaMicroserviceApplication.class, args);
	}

}
