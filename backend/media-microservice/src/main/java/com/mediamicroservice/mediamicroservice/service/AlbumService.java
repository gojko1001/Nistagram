package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.domain.Album;
import com.mediamicroservice.mediamicroservice.repository.IAlbumRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private IAlbumRepository albumRepository;



    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album findAlbumById(Long id){return albumRepository.findAlbumById(id);}
}
