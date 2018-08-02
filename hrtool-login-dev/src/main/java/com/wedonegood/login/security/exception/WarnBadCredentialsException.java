package com.wedonegood.login.security.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class WarnBadCredentialsException extends BadCredentialsException {
    public WarnBadCredentialsException(String msg) {
        super(msg);
    }
}
