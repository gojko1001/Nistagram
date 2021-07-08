package com.nistagram.authenticationmicroservice.saga.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetUsernameEvent extends GetBaseMessage {
    @JsonProperty("new_username")
    String newUsername;

    @JsonProperty("old_username")
    String oldUsername;
}
