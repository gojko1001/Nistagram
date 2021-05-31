package com.nistagram.usermicroservice.domain;

import com.nistagram.usermicroservice.domain.enums.FollowingStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FollowingList {

    @EmbeddedId
    private FollowingListKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("followingId")
    @JoinColumn(name = "following_id")
    private User following;

    @Column
    private FollowingStatus followingStatus;
    @Column
    private Boolean enableNotification = false;
}
