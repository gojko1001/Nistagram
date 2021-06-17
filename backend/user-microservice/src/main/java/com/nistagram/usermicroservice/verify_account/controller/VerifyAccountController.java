package com.nistagram.usermicroservice.verify_account.controller;

import com.nistagram.usermicroservice.service.interfaces.IUserService;
import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.controller.mapping.VerifyAccountMapper;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import com.nistagram.usermicroservice.verify_account.service.interfaces.IVerificationRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/verify")
@CrossOrigin(origins = "https://localhost:3000")
public class VerifyAccountController {

    @Autowired
    private IVerificationRequestService verificationRequestService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity getAllPending() {
        List<VerifyRequestDto> list = VerifyAccountMapper.mapVerificationRequestsToVerificationRequestDtos(userService.getUsersWithVerifyRequestPending());
        list = verificationRequestService.getIdImages(list);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping
    public VerificationRequest changeVerificationStatus(@RequestBody VerifyRequestDto verifyRequestDto) {
        return verificationRequestService.updateVerifyRequest(VerifyAccountMapper.mapVerifyRequestDtoToVerificationRequest(verifyRequestDto));
    }

}
