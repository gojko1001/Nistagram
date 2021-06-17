package com.nistagram.usermicroservice.verify_account.controller;

import com.nistagram.usermicroservice.domain.User;
import com.nistagram.usermicroservice.service.interfaces.IUserService;
import com.nistagram.usermicroservice.verify_account.controller.dto.ShowVerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.controller.dto.VerifyRequestDto;
import com.nistagram.usermicroservice.verify_account.controller.mapping.VerifyAccountMapper;
import com.nistagram.usermicroservice.verify_account.domain.UserCategory;
import com.nistagram.usermicroservice.verify_account.domain.VerificationRequest;
import com.nistagram.usermicroservice.verify_account.service.interfaces.IUserCategoryService;
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
    @Autowired
    private IUserCategoryService userCategoryService;

    @GetMapping
    public ResponseEntity getAllPending() {
        List<ShowVerifyRequestDto> list = VerifyAccountMapper.mapVerificationRequestsToShowVerificationRequestDtos(userService.getUsersWithVerifyRequestPending());
        list = verificationRequestService.getIdImages(list);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping
    public VerificationRequest changeVerificationStatus(@RequestBody VerifyRequestDto verifyRequestDto) {
        return verificationRequestService.updateVerifyRequest(VerifyAccountMapper.mapVerifyRequestDtoToVerificationRequest(verifyRequestDto));
    }

    @PostMapping
    public VerificationRequest createVerifyRequest(@RequestBody VerifyRequestDto verifyRequestDto){
        User user = userService.findUserByFullName(verifyRequestDto.getFullName());
        return verificationRequestService.createVerifyRequest(VerifyAccountMapper.mapVerifyRequestDtoToVerificationRequest(verifyRequestDto), user);
    }

    @GetMapping("/categories")
    public List<UserCategory> getAllCategories(){
        return userCategoryService.getAll();
    }

}
