package com.nistagram.messengermicroservice.service.interfaces;

import com.nistagram.messengermicroservice.saga.event.GetUsernameEvent;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    void cancelUpdateUsername(GetUsernameEvent event);

    void updateUsername(GetUsernameEvent event);
}
