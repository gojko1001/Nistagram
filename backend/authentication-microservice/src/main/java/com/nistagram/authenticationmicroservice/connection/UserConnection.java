package com.nistagram.authenticationmicroservice.connection;

import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import com.nistagram.authenticationmicroservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-microservice")
public interface UserConnection {

    @PostMapping("/user/add")
    void registerUser(@RequestBody UserCredentialsDto userReg);

    @PostMapping("/user/addGoogleUser")
    void registerGoogleUser(@RequestBody UserCredentialsDto userReg);

    @PostMapping("/user/find")
    UserDto getUserByEmail(@RequestBody String email);

    @GetMapping("/user//{username}")
    UserDto getUserByUsername(@PathVariable String username);
}
