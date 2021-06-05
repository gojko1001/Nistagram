package com.xws.nistagrammonolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NistagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(NistagramApplication.class, args);
    }

}
