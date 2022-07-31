package com.atguigu.jedis;

import redis.clients.jedis.Jedis;

public class JedisDemo {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.62.131", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }

}
