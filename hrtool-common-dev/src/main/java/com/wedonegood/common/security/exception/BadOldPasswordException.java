package com.wedonegood.common.security.exception;

public class BadOldPasswordException extends RuntimeException {
    public BadOldPasswordException(String message) {
        super(message);
    }
}
