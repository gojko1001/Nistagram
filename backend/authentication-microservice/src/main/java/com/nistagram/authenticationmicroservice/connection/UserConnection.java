package com.nistagram.authenticationmicroservice.connection;

import com.nistagram.authenticationmicroservice.dto.UserCredentialsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-microservice", url = "http://localhost:3032")
public interface UserConnection {

    @PostMapping("/user/add")
    void registerUser(@RequestBody UserCredentialsDto userReg);

    @PostMapping("/user/addGoogleUser")
    void registerGoogleUser(@RequestBody UserCredentialsDto userReg);

}
