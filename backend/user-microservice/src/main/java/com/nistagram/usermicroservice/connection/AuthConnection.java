package com.nistagram.usermicroservice.connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "authentication-microservice", url = "http://localhost:3031")
public interface AuthConnection {

    @PutMapping("/userCredentials/change_username")
    void changeUsername(@RequestBody String newUsername,
                        @RequestHeader("Authorization") String jwt);
}
