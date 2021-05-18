package com.xws.nistagrammonolith.controller.dto;

import com.xws.nistagrammonolith.domain.Comment;
import com.xws.nistagrammonolith.domain.Location;
import com.xws.nistagrammonolith.domain.Tag;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private String description;
    private Location location;
    private List<Tag> tags;
    private List<Comment> comments;
    private boolean isImage;
    private List<byte[]> imageBytes;
}
