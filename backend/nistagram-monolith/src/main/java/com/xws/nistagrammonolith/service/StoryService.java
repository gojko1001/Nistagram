package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.domain.media.Location;
import com.xws.nistagrammonolith.domain.media.Media;
import com.xws.nistagrammonolith.domain.media.Story;
import com.xws.nistagrammonolith.repository.IMediaRepository;
import com.xws.nistagrammonolith.repository.IStoryRepository;
import com.xws.nistagrammonolith.service.interfaces.IHashtagService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class StoryService implements IStoryService {
    @Autowired
    private IStoryRepository storyRepository;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private IHashtagService hashtagService;
    @Autowired
    private IMediaRepository mediaRepository;

    @Override
    public Story save(Story story) {
        log.info("Try to save story: " + story.getId());
        return storyRepository.save(story);
    }

    @Override
    public Story saveImageInfo(MediaDto imageDto) {
        Story story = new Story();
        Media media = new Media();
        media.setFileName(imageDto.getFileName());
        if (imageDto.getFileName().contains(".mp4")) {
            media.setImage(false);
        }
        media.setUsername(imageDto.getUsername());
        media.setDescription(imageDto.getDescription());
        Location location = locationService.findByName(imageDto.getLocationName());
        media.setLocation(location);
        media.setHashtags(hashtagService.createTags(imageDto.getTags()));
        media.setTimestamp(new Date());
        mediaRepository.save(media);
        story.setMedia(media);
        return save(story);
    }
}

