package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import com.mediamicroservice.mediamicroservice.domain.RequestStatus;
import com.mediamicroservice.mediamicroservice.repository.IInappropriateContentRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IInappropriateContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InappropriateContentService implements IInappropriateContentService {
    @Autowired
    private IInappropriateContentRepository inappropriateContentRepository;
    @Override
    public List<InappropriateContent> getAll() {
        return inappropriateContentRepository.findAll();
    }

    @Override
    public InappropriateContent create(CreateInappropriateContentDto inappropriateContent) {
        InappropriateContent inappropriateContent1 = new InappropriateContent();
        inappropriateContent1.setRequestedBy(inappropriateContent.getRequestedBy());
        inappropriateContent1.setRequestStatus(RequestStatus.PENDING);
        inappropriateContentRepository.save(inappropriateContent1);
        return inappropriateContent1;
    }
}
