package com.wedonegood.login.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wedonegood.login.model.user.UserService;
import com.wedonegood.login.otp.OTPService;
import com.wedonegood.login.security.RoleEnum;
import com.wedonegood.login.security.UserInfoContext;
import com.wedonegood.roles.api.FunctionService;

@Controller
public class SmsAuthController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private FunctionService functionService;

    @GetMapping("/login/code")
    public ModelAndView code(HttpServletResponse response) throws IOException {
        return showCode(null);
    }

    @GetMapping("/login/code-error")
    public ModelAndView codeError(HttpServletResponse response) throws IOException {
        return showCode("Wrong sms code");
    }

    @GetMapping("/login/code-warn")
    public ModelAndView codeWarn(HttpServletResponse response) throws IOException {
        return showCode("Wrong sms code. You have one remaining attempt, in case of an error, your account will be blocked");
    }

    private ModelAndView showCode(String message) {
        UserInfoContext uic = UserInfoContext.getCurrent();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("uic", uic);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("code");
        return modelAndView;
    }

    @PostMapping("/login/code/check")
    public void checkCode(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserInfoContext uic = UserInfoContext.getCurrent();
        if(otpService.checkCode(uic.getUserId(), code)) {
            userService.clearAttemtCnt(uic.getUserId());
            if(UiUtils.hasRole("CHANGE_PASSWORD")) {
                UiUtils.removeAuthority(RoleEnum.PRE_AUTH_USER);
                response.sendRedirect("/pwd-change");
                return;
            } else {
            	final List<RoleEnum> grantList = new ArrayList<RoleEnum>();
            	grantList.add(RoleEnum.USER);
            	
            	final List<RoleEnum> removeList = new ArrayList<RoleEnum>();
            	removeList.add(RoleEnum.PRE_AUTH_USER);
            	
            	final List<String> functionList = this.functionService.findFunctionByUserId(uic.getUserId()); 
            	
				for (final String function : functionList) {
					for (final RoleEnum re : RoleEnum.values()) {
	                    if (re.name().equals(function.toUpperCase()) && !grantList.contains(re)) {
	                    	grantList.add(re);
	                    }
	                }
				}
            	
            	final RoleEnum[] grant = new RoleEnum[grantList.size()];
            	final RoleEnum[] remove = new RoleEnum[removeList.size()];
            	
                UiUtils.changeAuthority(grantList.toArray(grant) , removeList.toArray(remove));
            }
            response.sendRedirect("/");
        } else {

            int cnt = userService.recordLoginFailure(uic.getUserEmail());
            if(cnt > 3) {
                response.sendRedirect("/login-locked");
                SecurityContextHolder.clearContext();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                return;
            }
            otpService.sendCode(uic.getUserId());
            if(cnt == 3) {
                response.sendRedirect("/login/code-warn");
            } else {
                response.sendRedirect("/login/code-error");
            }
        }
    }

    private static void grantAuthority() {

    }
}
