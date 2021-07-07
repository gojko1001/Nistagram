package com.nistagram.messengermicroservice.service.interfaces;

import com.nistagram.messengermicroservice.domain.MediaName;
import org.springframework.stereotype.Service;

@Service
public interface IMediaNameService {
    MediaName create(MediaName mediaName);
}
