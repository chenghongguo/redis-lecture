package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author hongguo_cheng
 * @date 2021/7/10
 */
public class IntegerTest {

    private static Jedis jedis;

    @BeforeClass
    public static void before() {
        jedis = new Jedis("localhost", 6379);
    }

    @AfterClass
    public static void after() {
        jedis = null;
    }

    @Test
    public void testIncrBy() {
        String number = jedis.set("number", "100");
        System.out.println(number);
        Long number1 = jedis.incr("number");
        System.out.println(number1);
    }
}
