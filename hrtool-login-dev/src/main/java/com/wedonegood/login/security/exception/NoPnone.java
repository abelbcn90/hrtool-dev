package com.wedonegood.login.security.exception;

import org.springframework.security.authentication.AccountStatusException;

public class NoPnone extends AccountStatusException {

    public NoPnone(String msg) {
        super(msg);
    }
}
