package com.nistagram.notificationmicroservice.repository;

import com.nistagram.notificationmicroservice.domain.MediaNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaNotificationRepository extends JpaRepository<MediaNotification, Long> {
    List<MediaNotification> getMediaNotificationsByNotifyUsername(String username);
}
