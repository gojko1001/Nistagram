package com.nistagram.campaignmicroservice.service.interfaces;

import com.nistagram.campaignmicroservice.domain.TargetAudience;

import java.util.List;

public interface ITargetAudienceService {

    TargetAudience save(TargetAudience audience);

    boolean isUserTargetedAudience(String username, List<TargetAudience> audience);
}
