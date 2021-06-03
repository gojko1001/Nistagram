package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.controller.dto.StoryBytesDto;
import com.xws.nistagrammonolith.domain.media.Story;

import java.util.List;

public interface IStoryService {
    Story save(Story story);
    Story saveImageInfo(MediaDto imageDto);
    List<StoryBytesDto> getImagesFiles(List<Story> stories);
    StoryBytesDto imageFile(Story story, String filePath);
    List<StoryBytesDto> validStories(List<StoryBytesDto> stories);
}
