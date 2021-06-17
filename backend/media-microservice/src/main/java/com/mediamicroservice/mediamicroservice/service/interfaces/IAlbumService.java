package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.Album;

public interface IAlbumService {
    Album save(Album album);
    Album findAlbumById(Long id);
}
