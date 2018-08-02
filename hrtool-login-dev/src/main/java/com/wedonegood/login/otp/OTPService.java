package com.wedonegood.login.otp;

import java.util.concurrent.ThreadLocalRandom;

public interface OTPService {

    void sendCode(Long userId) throws Exception;
    boolean checkCode(Long userId, String code);

    public static String getRandomCode(int length) {
        int max = 1;
        for(int i = 0; i < length; i++) {
            max = max * 10;
        }
        int val = ThreadLocalRandom.current().nextInt(max);
        String format = String.format("%%0%dd", length);
        return String.format(format, val);
    }
}
