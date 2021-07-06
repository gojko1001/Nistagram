package com.mediamicroservice.mediamicroservice.service.interfaces;


import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.controller.dto.StoryBytesDto;
import com.mediamicroservice.mediamicroservice.domain.Story;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStoryService {
    Story save(Story story);

    Story saveImageInfo(MediaDto imageDto);

    List<StoryBytesDto> getImagesFiles(List<Story> stories);

    StoryBytesDto imageFile(Story story, String filePath, boolean isImage);

    List<StoryBytesDto> validStories(List<StoryBytesDto> stories);

    List<StoryBytesDto> highlightedStories(List<StoryBytesDto> stories);

    ResponseEntity highlightStory(Long storyId);
}
