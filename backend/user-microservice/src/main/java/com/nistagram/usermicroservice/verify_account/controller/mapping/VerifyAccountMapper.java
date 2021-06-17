package com.nistagram.usermicroservice.verify_account.controller.mapping;

import com.nistagram.usermicroservice.domain.enums.VerificationStatus;
import com.nistagram.usermicroservice.verify_account.controller.dto.CreateVerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;

public class VerifyAccountMapper {

    public static VerificationRequest mapCreateVerifyRequestDtoToVerificationRequest(CreateVerifyRequestDto createVerifyRequestDto){
        VerificationRequest verificationRequest = new VerificationRequest();
        verificationRequest.setCategory(createVerifyRequestDto.getCategory());
        verificationRequest.setFilePath(createVerifyRequestDto.getFileName());
        verificationRequest.setStatus(VerificationStatus.PENDING);
        return verificationRequest;
    }
}
