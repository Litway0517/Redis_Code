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
    }


    @Test
    public void string() {
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

    }

    // 测试list
    @Test
    public void list() {
        jedis.select(14);
        // l1: a b c
        jedis.rpush("l1", "a", "b", "c");
        List<String> l1 = jedis.lrange("l1", 0, -1);
        l1.stream().forEach(System.out::println);

        // l1: a b
        jedis.rpop("l1");
        l1 = jedis.lrange("l1", 0, -1);
        System.out.println(l1);

        /*
            注意:
                虽然对list进行了操作, 但是l1还是原来的l1, 所以直接输出l1的话还是a b c, 并不是a b
         */

    }


    // 测试字符串
    @Test
    public void stringMset() {
        this.jedis.select(15);

        jedis.mset("k1", "v1", "k2", "v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

    }


}
