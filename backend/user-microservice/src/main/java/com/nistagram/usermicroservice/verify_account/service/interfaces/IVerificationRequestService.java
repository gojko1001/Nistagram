package com.nistagram.usermicroservice.verify_account.service.interfaces;

import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.verify_account.controller.dto.ShowVerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IVerificationRequestService {

    VerificationRequest createVerifyRequest(VerificationRequest verificationRequest, User user);

    VerificationRequest updateVerifyRequest(VerificationRequest verificationRequest);

    List<ShowVerifyRequestDto> getIdImages(List<ShowVerifyRequestDto> verifyRequestDtos);
}
