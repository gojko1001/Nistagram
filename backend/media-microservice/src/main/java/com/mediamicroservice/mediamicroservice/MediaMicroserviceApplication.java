package com.mediamicroservice.mediamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableEurekaClient
public class MediaMicroserviceApplication {

	@GetMapping("/media")
	public String getMediaMicroservice(){
		return "Media microservice called.";
	}

	public static void main(String[] args) {
		SpringApplication.run(MediaMicroserviceApplication.class, args);
	}

}
