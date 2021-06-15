package com.nistagram.usermicroservice.verify_account.service.interfaces;

import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import org.springframework.stereotype.Service;

@Service
public interface IVerificationRequestService {
    VerificationRequest createVerifyRequest(VerificationRequest verificationRequest);
}
