package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.MediaName;

public interface IMediaNameService {
    MediaName save(MediaName mediaName);

    MediaName findAlbumById(Long id);
}
