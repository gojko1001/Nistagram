package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.connection.UserConnection;
import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.controller.mapping.PostMapper;
import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.domain.Location;
import com.mediamicroservice.mediamicroservice.domain.Media;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.repository.IMediaRepository;
import com.mediamicroservice.mediamicroservice.repository.IPostRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IHashtagService;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UserConnection userConnection;


    private static String uploadDir = "user-photos";

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty())
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
    public Post saveImageInfo(MediaDto imageDto) {
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
    public ImageBytesDto getImageFile(Post post) {
        ImageBytesDto imageBytesDto = new ImageBytesDto();
        if (post != null) {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            imageBytesDto = imageFile(post, filePath);
        }
        return imageBytesDto;
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

    private List<Post> findAll() {
        log.info("Read all posts from database.");
        return postRepository.findAll();
    }

    public List<ImageBytesDto> searchTag(String tag) {
        List<Post> allPosts = findAll();
        List<Post> posts = new ArrayList<>();
        for (Post post : allPosts) {
            for (Hashtag hashtag : post.getMedia().getHashtags()) {
                if (hashtag.getName().toLowerCase().contains(tag.toLowerCase()))
                    posts.add(post);
            }
        }
        List<String> publicProfiles = userConnection.arePublic(getUsernamesByPost(posts));
        posts = filterPublicPostByUsernames(publicProfiles, posts);
        return getImagesFiles(posts);
    }

    public List<ImageBytesDto> searchLocation(String location) {
        log.info("Search for locations who contains " + location + " in their name");
        List<Post> posts = postRepository.searchLocation(location);
        List<String> publicProfiles = userConnection.arePublic(getUsernamesByPost(posts));
        posts = filterPublicPostByUsernames(publicProfiles, posts);
        return getImagesFiles(posts);
    }

    @Override
    public List<Post> getPublicPosts() {
        List<Post> posts = findAll();
        List<String> usernames = userConnection.getPublicUsers();

        return filterPublicPostByUsernames(usernames, posts);
    }

    private List<String> getUsernamesByPost(List<Post> posts) {
        List<String> usernames = new ArrayList<>();
        for (Post post : posts) {
            usernames.add(post.getMedia().getUsername());
        }
        Set<String> set = new HashSet<>(usernames);
        usernames = set.stream().collect(Collectors.toList());
        return usernames;
    }

    private List<Post> filterPublicPostByUsernames(List<String> usernames, List<Post> posts) {
        List<Post> publicPosts = new ArrayList<>();
        for (Post post : posts) {
            for (String username : usernames) {
                if (post.getMedia().getUsername().equals(username))
                    publicPosts.add(post);
            }
        }
        Set<Post> set = new HashSet<>(publicPosts);
        publicPosts = set.stream().collect(Collectors.toList());
        return publicPosts;
    }

}
