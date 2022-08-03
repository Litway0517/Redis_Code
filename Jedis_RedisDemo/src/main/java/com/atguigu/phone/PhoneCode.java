package com.atguigu.phone;

import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // 2- 每个手机号每天只能发送3次. 将验证码存储到Redis中 并且存储时间为2分钟
    public static void sendCode(String phoneNumber) {
        // 连接
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(13);

        // 拼接key
        // 手机发送次数
        String countKey = "VerifyCode" + phoneNumber + ":" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ":count";
        // 验证码key
        String codeKey = "VerifyCode" + phoneNumber + ":code";

        // 每个手机号每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            // 没有发送过验证码
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            // 发送次数 +1
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            // 发送次数大于3次
            System.out.println("每个手机号每天只能发送三次验证码！");
            jedis.close();
            return;
        }

        // 发送验证码到redis中
        jedis.setex(codeKey, 2 * 60, getCode());
        jedis.close();
    }

    // 3- 验证校验
    public static void getRedisCode(String phoneNumber, String code) {
        // 连接
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(13);

        // 从redis中获取验证码
        String codeKey = "VerifyCode" + phoneNumber + ":code";
        String redisCode = jedis.get(codeKey);

        // 判断
        if (!(redisCode == null)) {
            if (redisCode.equals(code)) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        } else {
            System.out.println("失败");
        }
        jedis.close();

    }


}
