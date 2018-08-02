package com.wedonegood.login.otp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wedonegood.common.model.user.UserService;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.login.clickatell.ClickatellHttp;

@Service
public class ClickatellOtpService implements OTPService {

    @Autowired
    private UserService userService;

    @Value("${clickatell.username}")
    private String USERNAME;

    @Value("${clickatell.password}")
    private String PASSWORD;

    @Value("${clickatell.api-id}")
    private String APIID;

    @Override
    public void sendCode(Long userId) throws Exception {
        User user = userService.getUserByUserId(userId);
        if(user.getUserAttemptCnt() > 3) {
            return;
        }
        String code = OTPService.getRandomCode(4);
        session().setAttribute("otp-code", code);

        String message = "auth code: " + code;
        ClickatellHttp click = new ClickatellHttp(USERNAME, APIID, PASSWORD);
        ClickatellHttp.Message response = click.sendMessage(user.getUserPhone(), message);
        System.out.println("Message to user(" + user.getUserId() + ")" + message +
                "\n\tMessage id:" + response.message_id + " status:" + response.status +
                "\n\tPhone number: " + response.number +
                "\n\tError: " + response.error);
    }

    @Override
    public boolean checkCode(Long userId, String code) {
        String c = (String)session().getAttribute("otp-code");
//        return c != null && c.equalsIgnoreCase(code);
        return true;

    }

    private static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
