package com.nistagram.usermicroservice.verify_account.service;

import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import com.nistagram.usermicroservice.verify_account.domain.VerificationStatus;
import com.nistagram.usermicroservice.verify_account.repository.VerificationRequestRepository;
import com.nistagram.usermicroservice.verify_account.service.interfaces.IVerificationRequestService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class VerificationRequestService implements IVerificationRequestService {

    @Autowired
    private VerificationRequestRepository verificationRequestRepository;

    private static String uploadDir = "id-photos";

    private VerificationRequest save(VerificationRequest verificationRequest) {
        return verificationRequestRepository.save(verificationRequest);
    }

    public VerificationRequest createVerifyRequest(VerificationRequest verificationRequest) {
        verificationRequest.setStatus(VerificationStatus.PENDING);
        return save(verificationRequest);
    }

    public VerificationRequest updateVerifyRequest(VerificationRequest verificationRequest) {
        VerificationRequest dbVerificationRequest = verificationRequestRepository.getVerificationRequestById(verificationRequest.getId());
        dbVerificationRequest.setApprovedByUsername(verificationRequest.getApprovedByUsername());
        dbVerificationRequest.setStatus(verificationRequest.getStatus());
        return save(dbVerificationRequest);

    }

    public List<VerifyRequestDto> getIdImages(List<VerifyRequestDto> verifyRequestDtos) {
        for (VerifyRequestDto verifyRequestDto : verifyRequestDtos) {
            verifyRequestDto.setImageBytes(getImage(verifyRequestDto.getFileName()));
        }
        return verifyRequestDtos;
    }

    private byte[] getImage(String fileName) {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/" + uploadDir + "/");

        File in = new File(filePath + fileName);
        try {
            return IOUtils.toByteArray(new FileInputStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
