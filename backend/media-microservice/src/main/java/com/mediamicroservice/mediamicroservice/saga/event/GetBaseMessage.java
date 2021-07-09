package com.mediamicroservice.mediamicroservice.saga.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetBaseMessage {
    @JsonProperty("version")
    public long version;

    @JsonProperty("sending_time")
    public String sendingTime;
}
