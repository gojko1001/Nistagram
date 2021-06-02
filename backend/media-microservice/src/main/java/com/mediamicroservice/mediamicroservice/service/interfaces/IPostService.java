package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.dto.ImageBytesDto;

import java.util.List;

public interface IPostService {
    Post save (Post post);
    List<Post> getUserImages(String username);
    List<ImageBytesDto> getImagesFiles(List<Post> posts);
    ImageBytesDto imageFile(Post post, String filePath);
}
