package com.nistagram.usermicroservice.domain;

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
}
