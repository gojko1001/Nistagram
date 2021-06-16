package com.mediamicroservice.mediamicroservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediamicroservice.mediamicroservice.domain.Comment;
import com.mediamicroservice.mediamicroservice.domain.Hashtag;
import com.mediamicroservice.mediamicroservice.domain.Like;
import com.mediamicroservice.mediamicroservice.domain.Location;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    private String description;
    private Location location;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+02:00")
    private Date timestamp;
    private List<Hashtag> hashtags;
    private List<Comment> comments;
    private boolean isImage;
    private List<Like> likes;
    private List<byte[]> imageBytes;
}
