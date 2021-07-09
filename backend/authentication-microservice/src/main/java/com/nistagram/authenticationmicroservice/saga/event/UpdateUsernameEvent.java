package com.nistagram.authenticationmicroservice.saga.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class UpdateUsernameEvent extends BaseMessage {
    @JsonProperty("new_username")
    String newUsername;

    @JsonProperty("old_username")
    String oldUsername;

}
