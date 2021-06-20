package com.nistagram.notificationmicroservice.service.interfaces;

import com.nistagram.notificationmicroservice.domain.ImageBytesDto;

import java.util.List;

public interface IMediaNotificationService {
    List<ImageBytesDto> getAllPosts();
}
