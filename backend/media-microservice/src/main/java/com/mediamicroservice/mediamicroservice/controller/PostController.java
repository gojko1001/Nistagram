package com.mediamicroservice.mediamicroservice.controller;

import com.mediamicroservice.mediamicroservice.controller.dto.ImageByte;
import com.mediamicroservice.mediamicroservice.controller.dto.ImageBytesDto;
import com.mediamicroservice.mediamicroservice.controller.dto.MediaDto;
import com.mediamicroservice.mediamicroservice.domain.Post;
import com.mediamicroservice.mediamicroservice.logger.Logger;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private static String uploadDir = "user-photos";
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IPostService postService;

    // for notification-microservice
    @GetMapping
    public List<ImageBytesDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public List<String> saveImage(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        Logger.info("Save image.", "");
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile mf : multipartFiles) {
            String fileName = StringUtils.cleanPath(mf.getOriginalFilename().replaceAll("\\s", ""));
            uploadDir = "user-photos";
            FileUploadUtil.saveFile(uploadDir, fileName, mf);       // TODO: Generate new filename to avoid name conflicts
            fileNames.add(fileName);
        }
        return fileNames;
    }


    @PostMapping("/info")
    public Long saveImageInfo(@RequestBody MediaDto mediaDto) {
        Logger.info("Save image info.", mediaDto.getUsername());
        return postService.saveImageInfo(mediaDto).getMedia().getId();
    }


    @GetMapping("/profile/{username}")
    public ResponseEntity getImagesByUsername(@PathVariable("username") String username) {
        Logger.info("Get images by username.", username);
        List<Post> userPosts = postRepository.findPostsByMedia_Username(username);
        return new ResponseEntity(postService.getImagesFiles(userPosts), HttpStatus.OK);
    }


    @GetMapping("/discover/{username}")
    public ResponseEntity getDiscoverImages(@PathVariable("username") String username) {
        return postService.getDiscoverImages(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity getImageById(@PathVariable("id") Long id) {
        Logger.info("Get image by id: " + id, "");
        return new ResponseEntity(postService.getImageFileById(id), HttpStatus.OK);
    }

    @GetMapping("/search_tag")
    public ResponseEntity search_tag(@RequestParam String tag) {
        Logger.info("Search tag: " + tag, "");
        return new ResponseEntity(postService.searchTag(tag), HttpStatus.OK);
    }

    @GetMapping("/search_location")
    public ResponseEntity search_location(@RequestParam String location) {
        Logger.info("Search location: " + location, "");
        return new ResponseEntity(postService.searchLocation(location), HttpStatus.OK);
    }

    @GetMapping("/bytes/{mediaId}")
    public ImageByte getBytes(@PathVariable("mediaId") Long mediaId) {
        return postService.getBytes(mediaId);
    }

    @GetMapping("/username/{id}")
    public String getUsernameById(@PathVariable Long id){
        return postService.getUsernameById(id);
    }


}
