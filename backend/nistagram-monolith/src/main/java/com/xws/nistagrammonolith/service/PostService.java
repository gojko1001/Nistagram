package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.controller.dto.ImageBytesDto;
import com.xws.nistagrammonolith.controller.dto.ImageDto;
import com.xws.nistagrammonolith.controller.mapping.ImageMapper;
import com.xws.nistagrammonolith.domain.Post;
import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.repository.IImageRepository;
import com.xws.nistagrammonolith.service.interfaces.IPostService;
import com.xws.nistagrammonolith.service.interfaces.ILocationService;
import com.xws.nistagrammonolith.service.interfaces.ITagService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private ITagService tagService;

    private static String uploadDir = "user-photos";

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
        return imageRepository.save(post);
    }

    @Override
    public List<Post> getUserImages(String username){ return imageRepository.findImagesByUsername(username);}

    @Override
    public Post saveImageInfo(ImageDto imageDto){
        Post post = new Post();
        post.setFileName(imageDto.getFileName());
        if(imageDto.getFileName().contains(".mp4")){
            post.setImage(false);
        }
        post.setUsername(imageDto.getUsername());
        post.setDescription(imageDto.getDescription());
        Location location = locationService.findByName(imageDto.getLocationName());
        post.setLocation(location);
        post.setTags(tagService.createTags(imageDto.getTags()));
        return save(post);
    }

    @Override
    public List<ImageBytesDto> getImagesFiles(List<Post> posts){
        List<ImageBytesDto> imageBytesDtos = new ArrayList<>();
        if(posts != null){
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            for(Post post : posts){
                imageBytesDtos.add(imageFile(post, filePath));
            }
        }
        return imageBytesDtos;
    }

    @Override
    public ImageBytesDto getImageFileById(Long id){
        Post post = imageRepository.findImageById(id);
        ImageBytesDto imageBytesDtos = new ImageBytesDto();
        if(post != null){
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/" + uploadDir + "/");
            imageBytesDtos = imageFile(post, filePath);
        }
        return imageBytesDtos;
    }

    @Override
    public ImageBytesDto imageFile(Post post, String filePath){
        ImageBytesDto imageBytesDtos = ImageMapper.mapImageToImageBytesDto(post);
        File in = new File(filePath + post.getFileName());
        try {
            imageBytesDtos.getImageBytes().add(IOUtils.toByteArray(new FileInputStream(in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytesDtos;
    }

    @Override
    public Post getById(Long id){
        return imageRepository.findImageById(id);
    }



}
