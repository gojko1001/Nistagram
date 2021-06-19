package com.mediamicroservice.mediamicroservice.connection;

import com.mediamicroservice.mediamicroservice.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
@Service
public class UserConnection {

    @Autowired
    private RestTemplate restTemplate;

    public List<String> arePublic(List<String> usernames) {
        String url = "http://localhost:8762/user-api/user/are_public";
        List<String> list = new ArrayList<>();
        try {
            ResponseEntity<String[]> response = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(usernames, new HttpHeaders()), String[].class, 1);
            list = Arrays.asList(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getPublicUsers() {
        String url = "http://localhost:8762/user-api/user/public_users";

        List<String> list = new ArrayList<>();
        try {
            ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
            list = Arrays.asList(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

} */

//@FeignClient(value = "user-microservice", url = "http://localhost:3032")
@FeignClient(name = "user-microservice")
public interface UserConnection {

    @PostMapping("/user/are_public")
    List<String> arePublic(@RequestBody List<String> usernames);

    @GetMapping("/user/public_users")
    List<String> getPublicUsers();

    @GetMapping("/user_relation//followings/{username}")
    List<UserDto> getFollowings(@PathVariable String username);

}
