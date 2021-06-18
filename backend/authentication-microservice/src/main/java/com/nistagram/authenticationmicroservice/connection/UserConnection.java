package com.nistagram.authenticationmicroservice.connection;

import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@FeignClient(value = "user-microservice", url = "http://localhost:3032")
//public interface UserConnection {
//
//    @PostMapping("/user/add")
//    void registerUser(@RequestBody UserCredentialsDto userReg);
//
//    @PostMapping("/user/addGoogleUser")
//    void registerGoogleUser(@RequestBody UserCredentialsDto userReg);
//
//    @PostMapping("/user/find")
//    UserDto getUserByEmail(@RequestBody String email);
//}
@Service
public class UserConnection {

    @Autowired
    private RestTemplate restTemplate;

    public void registerUser(UserCredentialsDto userReq) {
        String url = "http://localhost:8762/user-api/user/add";
        try {
            restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(userReq, new HttpHeaders()), Void.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerGoogleUser(UserCredentialsDto userReq) {
        String url = "http://localhost:8762/user-api/user/addGoogleUser";
        try {
            restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(userReq, new HttpHeaders()), Void.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDto getUserByEmail(String email) {
        String url = "http://localhost:8762/user-api/user/find";
        UserDto userDto = new UserDto();
        try {
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(email, new HttpHeaders()), UserDto.class, 1);
            userDto = response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

}
