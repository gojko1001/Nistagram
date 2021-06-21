package com.nistagram.usermicroservice.verify_account.controller.mapping;

import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.verify_account.controller.dto.ShowVerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;

import java.util.ArrayList;
import java.util.List;

public class VerifyAccountMapper {

    public static VerificationRequest mapVerifyRequestDtoToVerificationRequest(VerifyRequestDto verifyRequestDto){
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setId(verifyRequestDto.getId());
        verificationRequest.setCategory(verifyRequestDto.getCategory());
        verificationRequest.setFilePath(verifyRequestDto.getFileName());
        verificationRequest.setStatus(verifyRequestDto.getStatus());
        verificationRequest.setApprovedByUsername(verifyRequestDto.getApprovedByUsername());
        return verificationRequest;
    }

    public static List<ShowVerifyRequestDto> mapVerificationRequestsToShowVerificationRequestDtos(List<User> users){
        List<ShowVerifyRequestDto> verifyRequestDtos = new ArrayList<>();
        for(User user: users){
            verifyRequestDtos.add(mapVerificationRequestToShowVerificationRequestDto(user.getVerificationRequest(), user.getFullName()));
        }
        return verifyRequestDtos;
    }

    private static ShowVerifyRequestDto mapVerificationRequestToShowVerificationRequestDto(VerificationRequest verificationRequest, String fullName) {
        ShowVerifyRequestDto verifyRequestDto = new ShowVerifyRequestDto();
        verifyRequestDto.setId(verificationRequest.getId());
        verifyRequestDto.setApprovedByUsername(verifyRequestDto.getApprovedByUsername());
        verifyRequestDto.setFileName(verificationRequest.getFilePath());
        verifyRequestDto.setStatus(verificationRequest.getStatus());
        verifyRequestDto.setCategory(verificationRequest.getCategory());
        verifyRequestDto.setFullName(fullName);
        return verifyRequestDto;
    }
}
