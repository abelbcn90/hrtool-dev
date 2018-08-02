package com.wedonegood.login.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.security.RoleEnum;
import com.wedonegood.common.security.UserInfoContext;
import com.wedonegood.login.web.dto.Passwords;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/pwd-change")
    public ModelAndView change() {
        return showPwd(null);
    }

    private ModelAndView showPwd(String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.addObject("p", new Passwords());
        modelAndView.setViewName("change-pwd");
        return modelAndView;
    }

    @PostMapping("/pwd-change")
    public ModelAndView createUploader(@ModelAttribute Passwords passwords, HttpServletResponse response) throws IOException {
        if(passwords.getNewPassword() == null || passwords.getConfirmPassword() == null) {
            return showPwd("Please fill all fields on form");
        }
        if(!passwords.getNewPassword().equals(passwords.getConfirmPassword())) {
            return showPwd("Passwords do not match");
        }
        UserInfoContext uic = UserInfoContext.getCurrent();
        userService.changePassword(uic.getUserId(), passwords.getNewPassword());

        UiUtils.changeAuthority(new RoleEnum[]{ RoleEnum.USER } , new RoleEnum[]{ RoleEnum.CHANGE_PASSWORD });
        response.sendRedirect("/");
        return null;
    }
}
