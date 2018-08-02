package com.wedonegood.login.web;

import com.wedonegood.login.model.user.UserService;
import com.wedonegood.login.security.RoleEnum;
import com.wedonegood.login.security.UserInfoContext;
import com.wedonegood.login.web.dto.Passwords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
