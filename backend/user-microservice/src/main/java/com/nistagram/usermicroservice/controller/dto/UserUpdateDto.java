package com.nistagram.usermicroservice.controller.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private UserDto user;
    private boolean publicProfile;
    private boolean publicDM;
    private boolean taggable;
}
