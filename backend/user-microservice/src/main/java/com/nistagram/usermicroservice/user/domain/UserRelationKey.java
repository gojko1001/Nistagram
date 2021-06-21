package com.nistagram.usermicroservice.user.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserRelationKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "related_user_id")
    private Long relatedUserId;

    public UserRelationKey(){}

    public UserRelationKey(Long userId, Long relatedUserId) {
        this.userId = userId;
        this.relatedUserId = relatedUserId;
    }
}
