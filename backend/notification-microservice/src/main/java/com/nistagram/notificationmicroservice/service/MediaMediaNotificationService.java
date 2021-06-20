package com.nistagram.notificationmicroservice.service;

import com.nistagram.notificationmicroservice.connection.MediaConnection;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import com.nistagram.notificationmicroservice.domain.MediaNotification;
import com.nistagram.notificationmicroservice.repository.IMediaNotificationRepository;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}