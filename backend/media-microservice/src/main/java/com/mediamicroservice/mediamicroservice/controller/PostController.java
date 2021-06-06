package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.repository.IPostRepository;
import com.mediamicroservice.mediamicroservice.service.interfaces.IPostService;
import com.mediamicroservice.mediamicroservice.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class PostController {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IPostService postService;

    private static String uploadDir = "user-photos";

    @PostMapping
    public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));       //TODO: slucaj sa istim nazivima
        uploadDir = "user-photos";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return fileName;
    }


    @PostMapping("/info")
    public Post saveImageInfo(@RequestBody MediaDto imageDto) {
        return postService.saveImageInfo(imageDto);
    }


    @GetMapping("/profile/{username}")
    public ResponseEntity getImagesByUsername(@PathVariable("username") String username) {
        List<Post> userPosts = postRepository.findPostsByMedia_Username(username);
        return new ResponseEntity(postService.getImagesFiles(userPosts), HttpStatus.OK);
    }


    @GetMapping("/discover/{username}")
    public ResponseEntity getDiscoverImages(@PathVariable("username") String username) {
        List<Post> discoverPosts = postRepository.findAll();
        // TODO: provera da l je profil public i da l se prate ili je u pitanju gost
        // TODO: clean code
        return new ResponseEntity(postService.getImagesFiles(discoverPosts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getImageById(@PathVariable("id") Long id) {
        return new ResponseEntity(postService.getImageFileById(id), HttpStatus.OK);
    }

}