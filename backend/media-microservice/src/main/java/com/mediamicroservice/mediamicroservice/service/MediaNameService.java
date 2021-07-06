package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.MediaName;
import com.mediamicroservice.mediamicroservice.repository.IMediaNameRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IMediaNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaNameService implements IMediaNameService {
    @Autowired
    private IMediaNameRepository albumRepository;


    @Override
    public MediaName save(MediaName mediaName) {
        return albumRepository.save(mediaName);
    }

    @Override
    public MediaName findAlbumById(Long id) {
        return albumRepository.findMediaNameById(id);
    }
}
