package com.mediamicroservice.mediamicroservice.saga.event;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class MessageFactory {
    public static UpdateUsernameEvent createUpdateUsernameMessage(String newUsername, String oldUsername) {
        return UpdateUsernameEvent
                .builder()
                .newUsername(newUsername)
                .oldUsername(oldUsername)
                .version(1)
                .sendingTime(OffsetDateTime.now().toString())
                .build();
    }
}
