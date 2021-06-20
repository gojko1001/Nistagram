package com.nistagram.notificationmicroservice.repository;

import com.nistagram.notificationmicroservice.domain.MediaNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaNotificationRepository extends JpaRepository<MediaNotification, Long> {
}
