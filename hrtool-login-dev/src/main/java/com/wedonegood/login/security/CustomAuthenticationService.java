package com.wedonegood.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wedonegood.employee.user.api.model.entity.User;
import com.wedonegood.login.model.user.UserService;

public class CustomAuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().length() == 0) {
            throw new UsernameNotFoundException("Empty username");
        }

        User user = userService.getUserByUserEmail(username.trim());
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        
        return new UserInfoContextImpl(user, RoleEnum.PRE_AUTH_USER);
    }
}
