package com.nistagram.campaignmicroservice.connection;

import com.nistagram.campaignmicroservice.controller.dto.MediaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "media-microservice")
public interface MediaConnection {

    @PostMapping("/image/info")
    Long saveImageInfo(@RequestBody MediaDto mediaDto);

    @PostMapping("/story/info")
    Long saveStoryImageInfo(@RequestBody MediaDto mediaDto);

    @PostMapping("/image")
    List<String> saveImage(@RequestParam("file") List<MultipartFile> multipartFiles);
}
