package com.nistagram.notificationmicroservice.service;

import com.nistagram.notificationmicroservice.connection.MediaConnection;
import com.nistagram.notificationmicroservice.controller.dto.MediaNotificationDto;
import com.nistagram.notificationmicroservice.controller.mapping.MediaNotificationMapper;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;
import com.nistagram.notificationmicroservice.repository.IMediaNotificationRepository;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaMediaNotificationService implements IMediaNotificationService {
    @Autowired
    private MediaConnection mediaConnection;
    @Autowired
    private IMediaNotificationRepository mediaNotificationRepository;

    @Override
    public MediaNotification save(MediaNotification mediaNotification){
        return mediaNotificationRepository.save(mediaNotification);
    }

    @Override
    public List<ImageBytesDto> getAllPosts(){
        return mediaConnection.getAllPosts();
    }

    @Override
    public List<MediaNotificationDto> getMediaNotificationsByUsername(String username){
        List<MediaNotification> mediaNotifications = mediaNotificationRepository.getMediaNotificationsByNotifyUsername(username);
        List<MediaNotificationDto> mediaNotificationDtos = new ArrayList<>();
        for(MediaNotification mediaNotification : mediaNotifications){
            MediaNotificationDto mediaNotificationDto = MediaNotificationMapper.mapMediaNotificationToMediaNotificationDto(mediaNotification);
            mediaNotificationDto.setImageByte(mediaConnection.getBytes(mediaNotification.getMediaId()));
            mediaNotificationDtos.add(mediaNotificationDto);
        }
        return mediaNotificationDtos;
    }

}