package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.controller.dto.StoryBytesDto;
import com.xws.nistagrammonolith.controller.mapping.PostMapper;
import com.xws.nistagrammonolith.controller.mapping.StoryMapper;
import com.xws.nistagrammonolith.domain.media.Location;
import com.xws.nistagrammonolith.domain.media.Media;
import com.xws.nistagrammonolith.domain.media.Post;
import com.xws.nistagrammonolith.domain.media.Story;
import com.xws.nistagrammonolith.repository.IMediaRepository;
import com.xws.nistagrammonolith.repository.IStoryRepository;
import com.xws.nistagrammonolith.service.interfaces.IHashtagService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.IStoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static String uploadDir = "user-photos";

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

    @Override
    public List<StoryBytesDto> validStories(List<StoryBytesDto> stories){
        List<StoryBytesDto> validStories = new ArrayList<>();
        Date now = new Date();
        for(StoryBytesDto storyBytesDto: stories){
            if((storyBytesDto.getTimestamp().getTime() + 86400000) > now.getTime())
                validStories.add(storyBytesDto);
        }
        return validStories;
    }

    @Override
    public List<StoryBytesDto> getImagesFiles(List<Story> stories) {
        List<StoryBytesDto> storyBytesDto = new ArrayList<>();
        if (stories != null) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            for (Story story : stories) {
                storyBytesDto.add(imageFile(story, filePath));
            }
        }
        return storyBytesDto;
    }

    @Override
    public StoryBytesDto imageFile(Story story, String filePath) {
        StoryBytesDto storyBytesDto = StoryMapper.mapStoryToStoryBytesDto(story);
        File in = new File(filePath + story.getMedia().getFileName());
        try {
            storyBytesDto.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storyBytesDto;
    }
}

