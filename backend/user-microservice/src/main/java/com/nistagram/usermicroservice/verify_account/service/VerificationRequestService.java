package com.nistagram.usermicroservice.verify_account.service;

import com.nistagram.usermicroservice.verify_account.service.interfaces.IVerificationRequestService;
import com.nistagram.usermicroservice.verify_account.repository.VerificationRequestRepository;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationRequestService implements IVerificationRequestService {

    @Autowired
    private VerificationRequestRepository verificationRequestRepository;

    public VerificationRequest createVerifyRequest(VerificationRequest verificationRequest) {
        return verificationRequestRepository.save(verificationRequest);
    }
}
