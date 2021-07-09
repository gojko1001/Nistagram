package com.nistagram.authenticationmicroservice.saga.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class BaseMessage {
    @JsonProperty("version")
    public long version;

    @JsonProperty("sending_time")
    public String sendingTime;
}
