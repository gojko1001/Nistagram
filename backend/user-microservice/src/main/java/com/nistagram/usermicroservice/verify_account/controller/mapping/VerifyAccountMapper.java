package com.nistagram.usermicroservice.verify_account.controller.mapping;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;

import java.util.ArrayList;
import java.util.List;

public class VerifyAccountMapper {

    public static VerificationRequest mapVerifyRequestDtoToVerificationRequest(VerifyRequestDto verifyRequestDto){
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setCategory(verifyRequestDto.getCategory());
        verificationRequest.setFilePath(verifyRequestDto.getFileName());
        verificationRequest.setStatus(verifyRequestDto.getStatus());
        verificationRequest.setApprovedByUsername(verifyRequestDto.getApprovedByUsername());
        return verificationRequest;
    }
    public static VerifyRequestDto mapVerificationRequestToVerificationRequestDto(VerificationRequest verificationRequest, String fullName){
        VerifyRequestDto verifyRequestDto = new VerifyRequestDto();
        verifyRequestDto.setId(verificationRequest.getId());
        verifyRequestDto.setApprovedByUsername(verifyRequestDto.getApprovedByUsername());
        verifyRequestDto.setFileName(verificationRequest.getFilePath());
        verifyRequestDto.setStatus(verificationRequest.getStatus());
        verifyRequestDto.setCategory(verificationRequest.getCategory());
        verifyRequestDto.setFullName(fullName);
        return verifyRequestDto;
    }
    public static List<VerifyRequestDto> mapVerificationRequestsToVerificationRequestDtos(List<User> users){
        List<VerifyRequestDto> verifyRequestDtos = new ArrayList<>();
        for(User user: users){
            verifyRequestDtos.add(mapVerificationRequestToVerificationRequestDto(user.getVerificationRequest(), user.getFullName()));
        }
        return verifyRequestDtos;
    }
}
