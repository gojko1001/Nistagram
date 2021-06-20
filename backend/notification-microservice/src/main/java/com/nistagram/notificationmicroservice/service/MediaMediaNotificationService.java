package com.nistagram.notificationmicroservice.service;

import com.nistagram.notificationmicroservice.connection.MediaConnection;
import com.nistagram.notificationmicroservice.domain.Comment;
import com.nistagram.notificationmicroservice.domain.ImageBytesDto;
import com.nistagram.notificationmicroservice.service.interfaces.IMediaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaMediaNotificationService implements IMediaNotificationService {
    @Autowired
    private MediaConnection mediaConnection;

    @Override
    public List<ImageBytesDto> getAllPosts(){
        List<ImageBytesDto> imageBytesDtoList = mediaConnection.getAllPosts();
        ImageBytesDto imageBytesDto = imageBytesDtoList.get(0);
        for(Comment comment : imageBytesDto.getComments()){
            System.out.println("Komentar " + comment.getText());
        }
        return imageBytesDtoList;
    }
}