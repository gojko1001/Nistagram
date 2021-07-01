package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.repository.IPromoteRequestRepository;
import com.nistagram.campaignmicroservice.service.interfaces.IPromoteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoteRequestService implements IPromoteRequestService {
    @Autowired
    private IPromoteRequestRepository promoteRequestRepository;

}
