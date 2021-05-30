package com.nistagram.userMicroservice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FollowingListKey implements Serializable {
    @Column
    private Long userId;
    @Column
    private Long followingId;
}
