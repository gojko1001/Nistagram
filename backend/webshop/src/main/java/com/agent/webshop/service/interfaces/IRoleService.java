package com.agent.webshop.service.interfaces;

import com.agent.webshop.domain.Role;

public interface IRoleService {
    Role findRoleByName(String name);
}
