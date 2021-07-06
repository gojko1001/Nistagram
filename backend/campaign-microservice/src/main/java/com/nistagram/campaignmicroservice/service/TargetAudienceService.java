package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.domain.TargetAudience;
import com.nistagram.campaignmicroservice.repository.ITargetAudienceRepository;
import com.nistagram.campaignmicroservice.service.interfaces.ITargetAudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetAudienceService implements ITargetAudienceService {
    @Autowired
    private ITargetAudienceRepository targetAudienceRepository;

    public TargetAudience save(TargetAudience audience){
        return targetAudienceRepository.save(audience);
    }
}
