package com.nistagram.messengermicroservice.connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-microservice")
public interface UserConnection {
    @GetMapping("/user/is_public/{username}")
    boolean isPublic(@PathVariable String username);

    @GetMapping("/user_relation/is_follow/{followerUsername}/{followingUsername}")
    boolean isFollow(@PathVariable String followerUsername, @PathVariable String followingUsername);
}
