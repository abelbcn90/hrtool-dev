package com.wedonegood.login.security.exception;

public class BadOldPasswordException extends RuntimeException {
    public BadOldPasswordException(String message) {
        super(message);
    }
}
