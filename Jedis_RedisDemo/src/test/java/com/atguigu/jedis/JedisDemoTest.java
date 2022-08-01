package com.atguigu.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class JedisDemoTest {

    private Jedis jedis;

    @Before
    public void jedis() {
        this.jedis = new Jedis("localhost", 6379);
        this.jedis.select(15);
    }


    @Test
    public void string() {
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

    }


    @Test
    public void stringMset() {
        jedis.mset("k1", "v1", "k2", "v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

    }


}
