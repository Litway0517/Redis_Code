package com.atguigu.phone;

import org.junit.Test;

public class PhoneCodeTest {

    @Test
    public void code() {
        String code = PhoneCode.getCode();
        System.out.println(code);
    }

    @Test
    public void verifyCode() {
        String phoneNumber = "13755296636";
        // PhoneCode.sendCode(phoneNumber);
        PhoneCode.getRedisCode(phoneNumber, "708563");

    }

}
