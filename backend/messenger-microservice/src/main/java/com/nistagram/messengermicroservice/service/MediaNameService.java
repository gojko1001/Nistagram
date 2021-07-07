package com.nistagram.messengermicroservice.service;

import com.nistagram.messengermicroservice.domain.MediaName;
import com.nistagram.messengermicroservice.repository.MediaNameRepository;
import com.nistagram.messengermicroservice.service.interfaces.IMediaNameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MediaNameService implements IMediaNameService {

    private final MediaNameRepository mediaNameRepository;

    @Override
    public MediaName create(MediaName mediaName) {
        return mediaNameRepository.save(mediaName);
    }
}
