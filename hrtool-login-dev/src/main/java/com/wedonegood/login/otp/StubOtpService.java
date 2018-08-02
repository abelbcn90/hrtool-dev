package com.wedonegood.login.otp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wedonegood.employee.user.api.model.entity.User;
import com.wedonegood.login.model.user.UserService;

//@Service
public class StubOtpService implements OTPService {

    @Autowired
    private UserService userService;

    @Override
    public void sendCode(Long userId) {
        User user = userService.getUserByUserId(userId);
        if(user.getUserAttemptCnt() > 3) {
            return;
        }
        String code = OTPService.getRandomCode(4);
        session().setAttribute("otp-code", code);
        //send otp
        System.out.println(code);
    }

    @Override
    public boolean checkCode(Long userId, String code) {
        String c = (String)session().getAttribute("otp-code");
        return c != null && c.equalsIgnoreCase(code);

    }

    private static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
