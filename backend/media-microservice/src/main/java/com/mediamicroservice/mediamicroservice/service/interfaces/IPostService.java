package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Post;

import java.util.List;

public interface IPostService {

    List<Post> getAll();

    Post save(Post post);

    List<Post> getUserImages(String username);

    Post saveImageInfo(MediaDto imageDto);

    List<ImageBytesDto> getImagesFiles(List<Post> posts);
    ImageBytesDto getImageFile(Post post);

    ImageBytesDto imageFile(Post post, String filePath);

    Post getById(Long id);

    ImageBytesDto getImageFileById(Long id);

    List<ImageBytesDto> searchTag(String tag);

    List<ImageBytesDto> searchLocation(String location);
}
