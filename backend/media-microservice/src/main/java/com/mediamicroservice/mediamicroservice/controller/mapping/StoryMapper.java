package com.mediamicroservice.mediamicroservice.controller.mapping;

import com.mediamicroservice.mediamicroservice.controller.dto.StoryBytesDto;
import com.mediamicroservice.mediamicroservice.domain.Story;

import java.util.ArrayList;

public class StoryMapper {
    public static StoryBytesDto mapStoryToStoryBytesDto(Story story) {
        StoryBytesDto storyBytesDto = new StoryBytesDto();
        storyBytesDto.setId(story.getId());
        storyBytesDto.setUsername(story.getMedia().getUsername());
        storyBytesDto.setDescription(story.getMedia().getDescription());
        storyBytesDto.setLocation(story.getMedia().getLocation());
        storyBytesDto.setHashtags(story.getMedia().getHashtags());
        storyBytesDto.setImageBytes(new ArrayList<>());
        storyBytesDto.setTimestamp(story.getMedia().getTimestamp());
        storyBytesDto.setMediaId(story.getMedia().getId());
        return storyBytesDto;
    }
}
