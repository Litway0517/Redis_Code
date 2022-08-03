package com.atguigu.phone;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneCodeTest {

    @Test
    public void code() {
        String code = PhoneCode.getCode();
        System.out.println(code);
    }

}
