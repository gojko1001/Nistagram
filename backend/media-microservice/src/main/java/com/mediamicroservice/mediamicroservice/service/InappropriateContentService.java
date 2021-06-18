package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import com.mediamicroservice.mediamicroservice.domain.Media;
import com.mediamicroservice.mediamicroservice.domain.RequestStatus;
import com.mediamicroservice.mediamicroservice.repository.IInappropriateContentRepository;
import com.mediamicroservice.mediamicroservice.repository.IMediaRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IInappropriateContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InappropriateContentService implements IInappropriateContentService {
    @Autowired
    private IInappropriateContentRepository inappropriateContentRepository;
    @Autowired
    private IMediaRepository mediaRepository;

    @Override
    public List<InappropriateContent> getAll() {
        return inappropriateContentRepository.findAll();
    }

    @Override
    public InappropriateContent create(CreateInappropriateContentDto inappropriateContent) {
        InappropriateContent inappropriateContent1 = new InappropriateContent();
        inappropriateContent1.setRequestedBy(inappropriateContent.getRequestedBy());
        inappropriateContent1.setRequestStatus(RequestStatus.PENDING);

        Media media= mediaRepository.findMediaById(inappropriateContent.getMediaId());
        inappropriateContent1.setMedia(media);
        inappropriateContentRepository.save(inappropriateContent1);
        return inappropriateContent1;
    }
}
