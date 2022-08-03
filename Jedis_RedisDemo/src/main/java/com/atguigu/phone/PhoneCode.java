package com.atguigu.phone;

import java.util.Random;

public class PhoneCode {

    // 1- 生成6位验证码
    public static String getCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code.append(rand);
        }
        return code.toString();
    }


}
