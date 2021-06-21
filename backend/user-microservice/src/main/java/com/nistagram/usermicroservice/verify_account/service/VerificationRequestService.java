package com.nistagram.usermicroservice.verify_account.service;

import com.nistagram.usermicroservice.user.domain.User;
import com.nistagram.usermicroservice.user.service.interfaces.IUserService;
import com.nistagram.usermicroservice.verify_account.controller.dto.ShowVerifyRequestDto;
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
    @Autowired
    private IUserService userService;

    private static String uploadDir = "id-photos";

    private VerificationRequest save(VerificationRequest verificationRequest) {
        return verificationRequestRepository.save(verificationRequest);
    }

    public VerificationRequest createVerifyRequest(VerificationRequest verificationRequest, User user) {
        verificationRequest.setStatus(VerificationStatus.PENDING);
        VerificationRequest dbRequest = save(verificationRequest);
        user.setVerificationRequest(verificationRequest);
        userService.save(user);
        return dbRequest;
    }

    public VerificationRequest updateVerifyRequest(VerificationRequest verificationRequest) {
        VerificationRequest dbVerificationRequest = verificationRequestRepository.getVerificationRequestById(verificationRequest.getId());
        if(verificationRequest.getApprovedByUsername() != null)
            dbVerificationRequest.setApprovedByUsername(verificationRequest.getApprovedByUsername());
        dbVerificationRequest.setStatus(verificationRequest.getStatus());
        return save(dbVerificationRequest);

    }

    public List<ShowVerifyRequestDto> getIdImages(List<ShowVerifyRequestDto> verifyRequestDtos) {
        for (ShowVerifyRequestDto verifyRequestDto : verifyRequestDtos) {
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
