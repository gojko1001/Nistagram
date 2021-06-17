package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.controller.dto.StoryBytesDto;
import com.mediamicroservice.mediamicroservice.controller.mapping.StoryMapper;
import com.mediamicroservice.mediamicroservice.domain.*;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IMediaRepository;
import com.mediamicroservice.mediamicroservice.repository.IStoryRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IMediaNameService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IHashtagService;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IStoryService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private IMediaNameService albumService;

    private static String uploadDir = "user-photos";

    @Override
    public Story save(Story story) {
        Logger.infoDb("Try to save story: " + story.getId());
        Story dbStory = new Story();
        try {
            dbStory = storyRepository.save(story);
        } catch (Exception e) {
            Logger.errorDb("Cannot save story: " + story.getId(), e.getMessage());
        }
        return dbStory;
    }

    @Override
    public ResponseEntity saveImageInfo(MediaDto imageDto) {
        Long albumId = null;
        if(imageDto.getFileNames().size() > 1){
            MediaName mediaName = new MediaName();
            albumService.save(mediaName);
            albumId = mediaName.getId();
        }
        for(String fileName : imageDto.getFileNames()){
            Story story = new Story();
            Media media = new Media();
            //media.setFileName(fileName);
            /*if (fileName.contains(".mp4")) {
                media.setImage(false);
            }*/
            media.setUsername(imageDto.getUsername());
            media.setDescription(imageDto.getDescription());
            Location location = locationService.findByName(imageDto.getLocationName());
            media.setLocation(location);
            /*if(albumId != null){
                media.setMediaName(albumService.findAlbumById(albumId));
            }*/
            media.setHashtags(hashtagService.createTags(imageDto.getTags()));
            media.setTimestamp(new Date());
            //Logger.infoDb("Try to save media: " + media.getFileName());
            mediaRepository.save(media);
            story.setMedia(media);
            save(story);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public List<StoryBytesDto> validStories(List<StoryBytesDto> stories) {
        List<StoryBytesDto> validStories = new ArrayList<>();
        Date now = new Date();
        for (StoryBytesDto storyBytesDto : stories) {
            if ((storyBytesDto.getTimestamp().getTime() + 86400000) > now.getTime())
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
        /*File in = new File(filePath + story.getMedia().getFileName());
        try {
            storyBytesDto.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return storyBytesDto;
    }
}

