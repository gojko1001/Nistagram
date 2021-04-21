package com.xws.nistagrammonolith.service.interfaces;

import com.xws.nistagrammonolith.domain.User;
import java.util.List;

public interface IUserService {
    List<User> getAll();
    User create(User user);
}
