package com.nistagram.campaignmicroservice.controller.dto;

import com.nistagram.campaignmicroservice.domain.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String username;
    private Date birthDate;
    private Gender userGender;
}
