package com.atguigu.redis_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisTest")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/string")
    public String testRedis() {
        // 设置值
        redisTemplate.opsForValue().set("name", "lucy");
        // 获取
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }

}
