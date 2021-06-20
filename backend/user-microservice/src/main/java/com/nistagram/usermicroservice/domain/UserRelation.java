package com.nistagram.usermicroservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nistagram.usermicroservice.domain.enums.RelationStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserRelation {

    @EmbeddedId
    private UserRelationKey id;

    @ManyToOne
    @JsonBackReference
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonBackReference
    @MapsId("relatedUserId")
    @JoinColumn(name = "related_user_id")
    private User relatedUser;

    @Column
    private RelationStatus relationStatus;
    @Column
    private Boolean mutePost = false;
    @Column
    private Boolean muteStory = false;
    @Column
    private Boolean notifyPost = false;
    @Column
    private Boolean notifyStory = false;

    public UserRelation() {}

    public UserRelation(User user, User relatedUser, RelationStatus relationStatus) {
        this.user = user;
        this.relatedUser = relatedUser;
        this.relationStatus = relationStatus;
    }
}
