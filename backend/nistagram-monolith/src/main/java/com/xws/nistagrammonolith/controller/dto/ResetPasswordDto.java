package com.xws.nistagrammonolith.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String password;
    private String repeatPassword;

    public ResetPasswordDto(){}

    public ResetPasswordDto(String password, String repeatPassword) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }
}
