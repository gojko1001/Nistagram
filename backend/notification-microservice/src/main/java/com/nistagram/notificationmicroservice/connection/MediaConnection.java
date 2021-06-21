package com.nistagram.notificationmicroservice.connection;

import com.nistagram.notificationmicroservice.domain.ImageByte;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "media-microservice")
public interface MediaConnection {

    @GetMapping("/image")
    List<ImageBytesDto> getAllPosts();

    @GetMapping("/image/bytes/{mediaId}")
    ImageByte getBytes(@PathVariable("mediaId") Long mediaId);

}
