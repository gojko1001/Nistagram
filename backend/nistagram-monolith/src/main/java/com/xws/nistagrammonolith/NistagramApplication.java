package com.xws.nistagrammonolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableEurekaClient
//@RestController
public class NistagramApplication {

    /*@GetMapping
    public String getMediaMicroservice(){
        return "Nistagram monolith called.";
    }*/

    public static void main(String[] args) {
        SpringApplication.run(NistagramApplication.class, args);
    }

}
