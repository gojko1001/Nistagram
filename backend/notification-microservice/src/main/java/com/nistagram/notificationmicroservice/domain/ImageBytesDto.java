package com.nistagram.notificationmicroservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class ImageBytesDto {
    private Long id;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+01:00")
    private Date timestamp;
    private List<Comment> comments;
    private List<ImageByte> imageBytes;
    private Long mediaId;
}