package com.nistagram.campaignmicroservice.connection;

import com.nistagram.campaignmicroservice.controller.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-microservice")
public interface UserConnection {

    @GetMapping("/{username}")
    UserDto getUserByUsername(@PathVariable String username);
}
