package com.wedonegood.login.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.wedonegood.login.model.user.entity.IUser;

public interface UserInfoContext extends UserDetails, IUser {

    static UserInfoContext getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserInfoContext) {
            return (UserInfoContext) authentication.getPrincipal();
        }
        return null;
    }
}
