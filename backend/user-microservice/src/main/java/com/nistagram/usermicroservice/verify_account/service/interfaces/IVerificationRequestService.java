package com.nistagram.usermicroservice.verify_account.service.interfaces;

import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVerificationRequestService {

    VerificationRequest createVerifyRequest(VerificationRequest verificationRequest);

    VerificationRequest updateVerifyRequest(VerificationRequest verificationRequest);

    List<VerifyRequestDto> getIdImages(List<VerifyRequestDto> verifyRequestDtos);
}
