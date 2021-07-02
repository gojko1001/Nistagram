package com.agent.webshop.security;

import com.agent.webshop.exception.NotFoundException;
import com.agent.webshop.service.interfaces.IUserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserCredentialsService userCredentialsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userCredentialsService.findByUsername(username);
        if (user == null)
            throw new NotFoundException("User with username:" + username + " not found!");
        return user;
    }
}
