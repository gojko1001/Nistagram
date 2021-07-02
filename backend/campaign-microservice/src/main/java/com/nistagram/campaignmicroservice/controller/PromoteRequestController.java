package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.service.interfaces.IPromoteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promote")
public class PromoteRequestController {
    @Autowired
    private IPromoteRequestService promoteRequestService;

}
