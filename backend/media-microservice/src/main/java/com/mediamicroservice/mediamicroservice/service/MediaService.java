package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Media;
import com.mediamicroservice.mediamicroservice.repository.IMediaRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MediaService implements IMediaService {
    @Autowired
    private IMediaRepository mediaRepository;

    @Override
    public Media save(Media media) {
        log.info("Try to save post: " + media.getId());
        return mediaRepository.save(media);
    }
}
