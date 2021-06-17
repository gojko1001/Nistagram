package com.mediamicroservice.mediamicroservice.service;

import com.mediamicroservice.mediamicroservice.connection.UserConnection;
import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.controller.mapping.PostMapper;
import com.mediamicroservice.mediamicroservice.domain.*;
import com.mediamicroservice.mediamicroservice.logger.Logger;
import com.mediamicroservice.mediamicroservice.repository.IAlbumRepository;
import com.mediamicroservice.mediamicroservice.repository.IMediaRepository;
import com.mediamicroservice.mediamicroservice.repository.IPostRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IHashtagService;
import com.mediamicroservice.mediamicroservice.service.interfaces.ILocationService;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private IAlbumRepository albumRepository;


    private static String uploadDir = "user-photos";

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty())
            Logger.infoDb("There is no any posts.");
        return posts;
    }

    @Override
    public Post save(Post post) {
        Logger.infoDb("Try to save post: " + post.getId());
        Post dbPost = new Post();
        try {
            dbPost = postRepository.save(post);
        } catch (Exception e) {
            Logger.errorDb("Cannot save post: " + post.getId(), e.getMessage());
        }
        return dbPost;
    }

    @Override
    public List<Post> getUserImages(String username) {
        Logger.infoDb("Try to get post by username: " + username);
        List<Post> posts = postRepository.findPostsByMedia_Username(username);
        if (posts.isEmpty())
            Logger.info("There is no any post", username);
        return posts;
    }

    @Override
    public ResponseEntity saveImageInfo(MediaDto imageDto) {
        Long albumId = null;
        if(imageDto.getFileNames().size() > 1){
            Album album = new Album();
            albumRepository.save(album);
            albumId = album.getId();
        }
        for(String fileName : imageDto.getFileNames()){
            Post post = new Post();
            Media media = new Media();
            media.setFileName(fileName);
            if (fileName.contains(".mp4")) {
                media.setImage(false);
            }
            media.setUsername(imageDto.getUsername());
            media.setDescription(imageDto.getDescription());
            Location location = locationService.findByName(imageDto.getLocationName());
            media.setLocation(location);
            if(albumId != null){
                media.setAlbum(albumRepository.findAlbumById(albumId));
            }
            media.setHashtags(tagService.createTags(imageDto.getTags()));
            media.setTimestamp(new Date());
            media.setAlbum(media.getAlbum());
            mediaRepository.save(media);
            post.setMedia(media);
            save(post);
        }
        return new ResponseEntity(HttpStatus.OK);
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
        Logger.infoDb("Try to get post by id: " + id);
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
        Logger.infoDb("Try to get post with id: " + id);
        Post post = postRepository.findPostById(id);
        if (post == null)
            Logger.infoDb("There is no post with id: " + id);
        return post;
    }

    private List<Post> findAll() {
        Logger.infoDb("Read all posts from database.");
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty())
            Logger.infoDb("There is no any post.");
        return posts;
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
        Logger.infoDb("Search for locations who contains " + location + " in their name");
        List<Post> posts = postRepository.searchLocation(location);
        List<String> publicProfiles = userConnection.arePublic(getUsernamesByPost(posts));
        posts = filterPublicPostByUsernames(publicProfiles, posts);
        return getImagesFiles(posts);
    }

    @Override
    public List<Post> getPublicPosts() {
        List<Post> posts = findAll();
        List<String> usernames = userConnection.getPublicUsers();
        List<Post> postsToShow = new ArrayList<>();
        for(String username : usernames){
            List<Post> userPosts = postRepository.findPostsByMedia_Username(username);
            for(Post post : userPosts){
                postsToShow.add(post);
            }
        }
        return postsToShow;
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
