package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.PostDto;
import com.xws.nistagrammonolith.controller.mapping.PostMapper;
import com.xws.nistagrammonolith.domain.media.Location;
import com.xws.nistagrammonolith.domain.media.Media;
import com.xws.nistagrammonolith.domain.media.Post;
import com.xws.nistagrammonolith.repository.IMediaRepository;
import com.xws.nistagrammonolith.repository.IPostRepository;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import com.xws.nistagrammonolith.service.interfaces.IHashtagService;
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
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private IHashtagService tagService;
    @Autowired
    private IMediaRepository mediaRepository;

    private static String uploadDir = "user-photos";

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        if(posts.isEmpty())
            log.info("There is no any posts.");
        return posts;
    }

    @Override
    public Post save(Post post) {
        log.info("Try to save post: " + post.getId());
        return postRepository.save(post);
    }

    @Override
    public List<Post> getUserImages(String username) {
        log.info("Try to get post by username: " + username);
        return postRepository.findPostsByMedia_Username(username);
    }

    @Override
    public Post saveImageInfo(PostDto imageDto) {
        Post post = new Post();
        Media media = new Media();
        media.setFileName(imageDto.getFileName());
        if (imageDto.getFileName().contains(".mp4")) {
            media.setImage(false);
        }
        media.setUsername(imageDto.getUsername());
        media.setDescription(imageDto.getDescription());
        Location location = locationService.findByName(imageDto.getLocationName());
        media.setLocation(location);
        media.setHashtags(tagService.createTags(imageDto.getTags()));
        media.setTimestamp(new Date());
        mediaRepository.save(media);
        post.setMedia(media);
        return save(post);
    }

    @Override
    public List<ImageBytesDto> getImagesFiles(List<Post> posts) {
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        if (posts != null) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            for (Post post : posts) {
                imageBytesDtos.add(imageFile(post, filePath));
            }
        }
        return imageBytesDtos;
    }

    @Override
    public ImageBytesDto getImageFileById(Long id) {
        log.info("Try to get post by id: " + id);
        Post post = postRepository.findPostById(id);
        ImageBytesDto imageBytesDtos = new ImageBytesDto();
        if (post != null) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            imageBytesDtos = imageFile(post, filePath);
        }
        return imageBytesDtos;
    }

    @Override
    public ImageBytesDto imageFile(Post post, String filePath) {
        ImageBytesDto imageBytesDtos = PostMapper.mapPostToImageBytesDto(post);
        File in = new File(filePath + post.getMedia().getFileName());
        try {
            imageBytesDtos.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytesDtos;
    }

    @Override
    public Post getById(Long id) {
        log.info("Try to get post with id: " + id);
        return postRepository.findPostById(id);
    }


}
