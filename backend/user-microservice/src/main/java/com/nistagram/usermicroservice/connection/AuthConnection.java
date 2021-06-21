package com.nistagram.usermicroservice.connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication-microservice")
public interface AuthConnection {

    @PutMapping("/userCredentials/change_username/{newUsername}")
    void changeUsername(@PathVariable String newUsername,
                        @RequestHeader("Authorization") String jwt);
}

