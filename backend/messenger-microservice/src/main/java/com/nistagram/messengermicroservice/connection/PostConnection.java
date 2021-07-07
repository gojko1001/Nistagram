package com.nistagram.messengermicroservice.connection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "media-microservice")
public interface PostConnection {
    @GetMapping("/image/username/{id}")
    String getUsernameById(@PathVariable Long id);
}
