package com.xws.nistagrammonolith.controller.mapping;

import com.xws.nistagrammonolith.controller.dto.StoryBytesDto;
import com.xws.nistagrammonolith.domain.media.Story;

import java.util.ArrayList;

public class StoryMapper {
    public static StoryBytesDto mapStoryToStoryBytesDto(Story story) {
        StoryBytesDto storyBytesDto = new StoryBytesDto();
        storyBytesDto.setId(story.getId());
        storyBytesDto.setUsername(story.getMedia().getUsername());
        storyBytesDto.setDescription(story.getMedia().getDescription());
        storyBytesDto.setLocation(story.getMedia().getLocation());
        storyBytesDto.setHashtags(story.getMedia().getHashtags());
        storyBytesDto.setImage(story.getMedia().isImage());
        storyBytesDto.setImageBytes(new ArrayList<>());
        return storyBytesDto;
    }
}
