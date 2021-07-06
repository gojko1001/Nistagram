package com.nistagram.campaignmicroservice.service;

import com.nistagram.campaignmicroservice.domain.Ad;
import com.nistagram.campaignmicroservice.repository.IAdRepository;
import com.nistagram.campaignmicroservice.service.interfaces.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService implements IAdService {
    @Autowired
    private IAdRepository adRepository;

    public Ad save(Ad ad){
        return adRepository.save(ad);
    }
}
