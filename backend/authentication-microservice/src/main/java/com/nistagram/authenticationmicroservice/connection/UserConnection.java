package com.nistagram.authenticationmicroservice.connection;

import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-microservice", url = "http://localhost:3032")
public interface UserConnection {

    @PostMapping("/user/add")
    void registerUser(@RequestBody UserCredentialsDto userReg);

    @PostMapping("/user/addGoogleUser")
    void registerGoogleUser(@RequestBody UserCredentialsDto userReg);

    @PostMapping("/user/find")
    UserDto getUserByEmail(@RequestBody String email);
}