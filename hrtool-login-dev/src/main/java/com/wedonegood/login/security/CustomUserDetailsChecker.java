package com.wedonegood.login.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import com.wedonegood.login.security.exception.NoPnone;

public class CustomUserDetailsChecker implements UserDetailsChecker {

    protected final Log logger = LogFactory.getLog(this.getClass());

    public void check(UserDetails user) {
        if (!user.isCredentialsNonExpired()) {
            logger.debug("User account credentials have expired");
            throw new CredentialsExpiredException("User credentials have expired");
        }

        if(user instanceof UserInfoContextImpl) {
            UserInfoContextImpl uic = (UserInfoContextImpl) user;
            if(uic.getUserPhone() == null || uic.getUserPhone().length() == 0) {
                logger.debug("User does not have phone number");
                throw new NoPnone("User does not have phone number");
            }
        }
    }
}
