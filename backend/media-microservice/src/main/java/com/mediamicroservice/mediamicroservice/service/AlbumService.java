package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Album;
import com.mediamicroservice.mediamicroservice.repository.IAlbumRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IAlbumService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private IAlbumRepository albumRepository;
    @Autowired
    private IPostService postService;


    @Override
    public Album createAlbumAndMedia(List<MediaDto> mediaDtos) {
        Album album = new Album();
        for(MediaDto media: mediaDtos){
            media.setAlbum(album);
            postService.saveImageInfo(media);
        }
        return album;
    }
}
