package com.nistagram.notificationmicroservice.connection;

import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "media-microservice")
public interface MediaConnection {

    @GetMapping("/image")
    List<ImageBytesDto> getAllPosts();

}
