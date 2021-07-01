package com.nistagram.campaignmicroservice.controller;

import com.nistagram.campaignmicroservice.service.interfaces.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private IAdService adService;

}
