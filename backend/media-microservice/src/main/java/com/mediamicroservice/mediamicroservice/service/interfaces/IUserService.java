package com.mediamicroservice.mediamicroservice.service.interfaces;

import com.mediamicroservice.mediamicroservice.saga.event.GetUsernameEvent;
import com.mediamicroservice.mediamicroservice.saga.event.UpdateUsernameEvent;

public interface IUserService {

    void changeUsername(String newUsername, String oldUsername);

    void updateUsername(GetUsernameEvent event);

    void cancelUpdateUsername(GetUsernameEvent event);
}
