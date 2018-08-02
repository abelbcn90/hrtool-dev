package com.wedonegood.login.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.wedonegood.login.security.exception.NoPnone;
import com.wedonegood.login.security.exception.WarnBadCredentialsException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if(e instanceof LockedException) {
            getRedirectStrategy().sendRedirect(request, response, "/login-locked");
        } else if(e instanceof WarnBadCredentialsException) {
            getRedirectStrategy().sendRedirect(request, response, "/login-warn");
        } else if(e instanceof NoPnone){
            getRedirectStrategy().sendRedirect(request, response, "/login-no-phone");
        } else {
            getRedirectStrategy().sendRedirect(request, response, "/login-error");
        }
    }
}