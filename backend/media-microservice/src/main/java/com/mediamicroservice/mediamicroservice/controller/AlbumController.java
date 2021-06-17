package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Album;
import com.mediamicroservice.mediamicroservice.service.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private IAlbumService albumService;

    @PostMapping("/info")
    public Album saveAlbumInfo(@RequestBody List<MediaDto> mediaDtos) {
        return albumService.createAlbumAndMedia(mediaDtos);
    }
}
