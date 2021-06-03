package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.MediaDto;
import com.xws.nistagrammonolith.domain.media.Post;

import java.util.List;

public interface IPostService {

    List<Post> getAll();

    Post save(Post post);

    List<Post> getUserImages(String username);

    Post saveImageInfo(MediaDto imageDto);

    List<ImageBytesDto> getImagesFiles(List<Post> posts);

    ImageBytesDto imageFile(Post post, String filePath);

    Post getById(Long id);

    ImageBytesDto getImageFileById(Long id);
}
