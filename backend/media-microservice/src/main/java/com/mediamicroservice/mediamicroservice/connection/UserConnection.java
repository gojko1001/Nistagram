package com.mediamicroservice.mediamicroservice.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserConnection {

    @Autowired
    private RestTemplate restTemplate;

    public List<String> publicProfiles(List<String> usernames) {
        String url = "http://localhost:8762/user-api/user/are_public";
        List<String> response = new ArrayList<>();
        try {
            ResponseEntity<String[]> publicUsernames = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(usernames, new HttpHeaders()), String[].class, 1);
            response = Arrays.asList(publicUsernames.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public List<String> getPublicUsernames() {
        String url = "http://localhost:8762/user-api/user/public_users";
        List<String> response = new ArrayList<>();
        try {
            ResponseEntity<String[]> publicUsernames = restTemplate.exchange(url, HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()), String[].class, 1);
            response = Arrays.asList(publicUsernames.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }



}
