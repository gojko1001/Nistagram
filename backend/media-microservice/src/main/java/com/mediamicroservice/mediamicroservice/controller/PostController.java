package com.mediamicroservice.mediamicroservice.controller;

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
@CrossOrigin(origins = "https://localhost:3000")
public class PostController {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IPostService postService;

    private static String uploadDir = "user-photos";

    @PostMapping
    public List<String> saveImage(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        Logger.info("Save image.", "");
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile mf : multipartFiles){
            String fileName = StringUtils.cleanPath(mf.getOriginalFilename().replaceAll("\\s", ""));
            uploadDir = "user-photos";
            FileUploadUtil.saveFile(uploadDir, fileName, mf);
            fileNames.add(fileName);
        }
        return fileNames;
    }


    @PostMapping("/info")
    public ResponseEntity saveImageInfo(@RequestBody MediaDto mediaDto) {
        Logger.info("Save image info.", mediaDto.getUsername());
        return postService.saveImageInfo(mediaDto);
    }


    @GetMapping("/profile/{username}")
    public ResponseEntity getImagesByUsername(@PathVariable("username") String username) {
        Logger.info("Get images by username.", username);
        List<Post> userPosts = postRepository.findPostsByMedia_Username(username);
        return new ResponseEntity(postService.getImagesFiles(userPosts), HttpStatus.OK);
    }


    @GetMapping("/discover/{username}")
    public ResponseEntity getDiscoverImages(@PathVariable("username") String username) {
        //Logger.info("Get discover images.", username);
        List<Post> discoverPosts = postService.getPublicPosts();
        // TODO: provera da l je profil public i da l se prate ili je u pitanju gost
        // TODO: clean code
        return new ResponseEntity(postService.getImagesFiles(discoverPosts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getImageById(@PathVariable("id") Long id) {
        Logger.info("Get image by id: " + id, "");
        return new ResponseEntity(postService.getImageFileById(id), HttpStatus.OK);
    }

    @GetMapping("/search_tag")
    public ResponseEntity search_tag(@RequestParam String tag){
        Logger.info("Search tag: " + tag, "");
        return new ResponseEntity(postService.searchTag(tag), HttpStatus.OK);
    }

    @GetMapping("/search_location")
    public ResponseEntity search_location(@RequestParam String location){
        Logger.info("Search location: " + location, "");
        return new ResponseEntity(postService.searchLocation(location), HttpStatus.OK);
    }


}
