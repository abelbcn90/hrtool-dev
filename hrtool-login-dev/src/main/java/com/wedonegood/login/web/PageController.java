package com.wedonegood.login.web;

import com.wedonegood.login.security.UserInfoContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PageController {

    @GetMapping("/")
    public ModelAndView indexPage(HttpServletResponse response) throws IOException {
        UserInfoContext uic = UserInfoContext.getCurrent();
        ModelAndView modelAndView = new ModelAndView();
        if (uic == null) {
            modelAndView.setViewName("index");
        } else if (UiUtils.hasRole("USER")){
            modelAndView.addObject("uic", uic);
            modelAndView.setViewName("internal");
        } else if(UiUtils.hasRole("CHANGE_PWD")) {
            response.sendRedirect("/pwd-change");
            return null;
        } else {
            response.sendRedirect("/login/code");
            return null;
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return showLoginPage(null);
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
        return showLoginPage("Wrong email or password");
    }

    @GetMapping("/login-warn")
    public ModelAndView loginWarn() {
        return showLoginPage("Wrong user or password. You have one remaining attempt, in case of an error, your account will be blocked");
    }

    private ModelAndView showLoginPage(String message) {
        UserInfoContext uic = UserInfoContext.getCurrent();
        ModelAndView modelAndView = new ModelAndView();
        if (uic == null) {
            modelAndView = new ModelAndView();
            modelAndView.addObject("message", message);
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("uic", uic);
            modelAndView.setViewName("internal");
        }
        return modelAndView;
    }

    @PostMapping("/login")
    public String doLogin() {
        return null;
    }

    @GetMapping("/login-locked")
    public String block() {
        return "locked";
    }

    @GetMapping("/login-no-phone")
    public String noPhone() {
        return "no-phone";
    }
}
