package com.mediamicroservice.mediamicroservice.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthConnection {

    @Autowired
    private RestTemplate restTemplate;

    public String verifyUser(String username) {
        String url = "http://localhost:8762/user-api/verify/" + username;
        String text = "";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            text = response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}

//@FeignClient(value = "authentication-microservice", url = "http://localhost:3031")
//public interface AuthConnection {
//
//    @GetMapping("/verify/{username}")
//    String verifyUser(@PathVariable String username);
//}
