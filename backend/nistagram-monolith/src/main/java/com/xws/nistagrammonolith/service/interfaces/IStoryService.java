package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.domain.media.Story;

public interface IStoryService {
    Story save(Story story);
    Story saveImageInfo(MediaDto imageDto);
}
