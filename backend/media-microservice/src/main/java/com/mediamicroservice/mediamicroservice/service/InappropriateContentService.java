package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.CreateInappropriateContentDto;
import com.mediamicroservice.mediamicroservice.domain.InappropriateContent;
import com.mediamicroservice.mediamicroservice.domain.Media;
import com.mediamicroservice.mediamicroservice.domain.RequestStatus;
import com.mediamicroservice.mediamicroservice.exception.InvalidActionException;
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
        Media media= mediaRepository.findMediaById(inappropriateContent.getMediaId());
        List<InappropriateContent> inappropriateContentList = getAll();
        for(InappropriateContent ic:inappropriateContentList){
            if(ic.getMedia().equals(media) && ic.getRequestedBy().equals(inappropriateContent.getRequestedBy())){
                throw new InvalidActionException("You have already reported this content");
            }
        }
        inappropriateContent1.setRequestedBy(inappropriateContent.getRequestedBy());
        inappropriateContent1.setRequestStatus(RequestStatus.PENDING);


        inappropriateContent1.setMedia(media);

        inappropriateContentRepository.save(inappropriateContent1);
        return inappropriateContent1;
    }
}
