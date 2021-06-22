package com.nistagram.notificationmicroservice.connection;

import com.nistagram.notificationmicroservice.controller.dto.UserRelationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-microservice")
public interface UserConnection {

    @GetMapping("/user_relation")
    List<UserRelationDto> getAllRequestsAndFollowings();
}
