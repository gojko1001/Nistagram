package com.mediamicroservice.mediamicroservice.connection;

import com.mediamicroservice.mediamicroservice.controller.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-microservice")
public interface UserConnection {

    @PostMapping("/user/are_public")
    List<String> arePublic(@RequestBody List<String> usernames);

    @GetMapping("/user/public_users")
    List<String> getPublicUsers();

    @GetMapping("/user/public/taggable/users")
    List<String> getPublicTaggableUsers();

    @GetMapping("/user_relation//followings/{username}")
    List<UserDto> getFollowings(@PathVariable String username);

}
