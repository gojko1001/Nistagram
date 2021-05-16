package com.xws.nistagrammonolith.controller.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String password;
    private String repeatPassword;
}
