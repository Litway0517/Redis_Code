package com.atguigu.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

import static org.junit.Assert.*;

public class JedisDemoTest {

    private Jedis jedis;

    @Before
    public void jedis() {
        this.jedis = new Jedis("192.168.62.131", 6379);
    }


    @Test
    public void string() {
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

    }

}
