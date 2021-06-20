package com.nistagram.usermicroservice.connection;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@FeignClient(value = "authentication-microservice", url = "http://localhost:3031")
//public interface AuthConnection {
//
//    @PutMapping("/userCredentials/change_username")
//    void changeUsername(@RequestBody String newUsername,
//                        @RequestHeader("Authorization") String jwt);
//}
@Service
public class AuthConnection {

    @Autowired
    private RestTemplate restTemplate;

    public void changeUsername(String newUsername, String jwt) {
        String url = "http://localhost:8762/user-api/userCredentials/change_username";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", jwt);
            restTemplate.exchange(url, HttpMethod.PUT,
                    new HttpEntity<>(newUsername, headers), Void.class, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
