package com.nistagram.usermicroservice.user.controller;

import com.nistagram.usermicroservice.exception.UnauthorizedException;
import com.nistagram.usermicroservice.logger.Logger;
import com.nistagram.usermicroservice.user.controller.dto.AgentDto;
import com.nistagram.usermicroservice.user.controller.dto.UserDto;
import com.nistagram.usermicroservice.user.controller.dto.UserRegistrationDto;
import com.nistagram.usermicroservice.user.controller.mapper.UserMapper;
import com.nistagram.usermicroservice.user.service.interfaces.IUserService;
import com.nistagram.usermicroservice.verify_account.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final String PROFILE_PICS_DIR = "profile-photos";
    @Autowired
    private IUserService userService;
    //@Autowired
    //private JwtUtil jwtUtil;

    @GetMapping
    public List<UserDto> getAll() {
        Logger.info("Get all users", "");
        return UserMapper.mapUserListToUserDtoList(userService.getAll());
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        Logger.info("Get user by username", username);
        UserDto userDto = UserMapper.mapUserToUserDto(userService.findUserByUsername(username));
        if(userDto.getProfilePicPath() != null)
            userDto.setImageBytes(FileUploadUtil.getImage(userDto.getProfilePicPath(), PROFILE_PICS_DIR));
        return userDto;
    }

    @PostMapping("/find")
    public UserDto getUserByEmail(@RequestBody String email) {
        return UserMapper.mapUserToUserDto(userService.findUserByEmail(email));
    }

    @PostMapping("/add")
    public void registerUser(@RequestBody UserRegistrationDto userReg) {
        userService.registerUser(userReg, false);
    }

    @PostMapping("/addAgent")
    public void registerAgent(@RequestBody AgentDto userReg) {
        userService.registerAgent(userReg);
    }

    @PostMapping("/addGoogleUser")
    public void registerGoogleUser(@RequestBody UserRegistrationDto userReg) {
        userService.registerUser(userReg, true);
    }

    @PostMapping("/upload_pic")
    public String uploadProfilePic(@RequestParam("file") List<MultipartFile> multipartFile) throws IOException {
        return FileUploadUtil.saveImage(multipartFile.get(0), PROFILE_PICS_DIR);  // TODO: Set filename to userId
    }

    @PutMapping()
    public UserDto updateProfile(@RequestBody UserDto userDto,
                                 @RequestHeader("Authorization") String jwt) {
        // String username = jwtUtil.extractUsername(jwt);
        String username = "";
        if (username == null)
            throw new UnauthorizedException("Access denied");
        Logger.info("Try to edit user: " + username, userDto.getUsername());
        return UserMapper.mapUserToUserDto(userService.updateUser(UserMapper.mapUserDtoToUser(userDto), username, jwt));
    }

    @GetMapping("/search")
    public List<UserDto> search(@RequestParam String username) {
        log.info("Try to search users with username: " + username);
        return UserMapper.mapUserListToUserDtoList(userService.search(username));
    }

    @PostMapping("/are_public")
    public List<String> arePublic(@RequestBody List<String> usernames) {
        return userService.arePublic(usernames);
    }

    @GetMapping("/public_users")
    public List<String> getPublicUsers() {
        log.info("Get all public users");
        return userService.getPublicUsers();
    }

    @GetMapping("/public/taggable/users")
    public List<String> getPublicTaggableUsers() {
        return userService.getPublicTaggableUsers();
    }

    @GetMapping("/chatable_users/{username}/{searchInput}")
    public List<UserDto> getAllChatableUsersSearch(@PathVariable String username, @PathVariable String searchInput) {
        return userService.getChatableUsers(username, searchInput).stream()
                .map(UserMapper::mapUserToUserDto).collect(Collectors.toList());
    }

    @GetMapping("/chat_users/{username}")
    public List<UserDto> getAllChatableUsers(@PathVariable String username) {
        return userService.getChatUsers(username).stream()
                .map(UserMapper::mapUserToUserDto).collect(Collectors.toList());
    }

    @GetMapping("/is_public/{username}")
    public boolean isPublic(@PathVariable String username){
        return userService.isPublic(username);
    }
}
