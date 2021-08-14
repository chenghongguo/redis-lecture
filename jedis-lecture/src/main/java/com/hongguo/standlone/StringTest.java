package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GetExParams;

import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2021/7/10
 */
public class StringTest {

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
    public void testSet() {
        String set = jedis.set("key", "value");
        System.out.println(set);
    }

    @Test
    public void testSetEx() {
        String setEx = jedis.setex("key1", 200L, "value");
        System.out.println(setEx);
    }

    @Test
    public void testSetNx() {
        Long setNx = jedis.setnx("key", "value1");
        System.out.println(setNx);
        Long setNx2 = jedis.setnx("key2", "value2");
        System.out.println(setNx2);
    }

    @Test
    public void testMset() {
        String mset = jedis.mset("hello", "world", "hello2", "world2");
        System.out.println(mset);
        Long msetnx = jedis.msetnx("hello3", "world3", "hello4", "world4");
        System.out.println(msetnx);
        msetnx = jedis.msetnx("hello5", "world3", "hello4", "world4");
        System.out.println(msetnx);
    }

    @Test
    public void testGet() {
        String hello = jedis.get("hello");
        System.out.println(hello);
    }

    @Test
    public void testMget() {
        List<String> mget = jedis.mget("hello", "hello2");
        System.out.println(mget);
    }

    @Test
    public void testGetDel() {
        String hello = jedis.getDel("hello");
        System.out.println(hello);
        hello = jedis.get("hello");
        System.out.println(hello);
    }

    @Test
    public void testGetEx() {
        String hello2 = jedis.getEx("hello2", GetExParams.getExParams().persist());
        System.out.println(hello2);
    }

    @Test
    public void testGetSet() {
        String set = jedis.getSet("hello2", "worldddf");
        System.out.println(set);
    }

    @Test
    public void testGetRange() {
        String hello2 = jedis.getrange("hello2", -1, 100);
        System.out.println(hello2);
    }

    @Test
    public void testSetBit() {
        Boolean hello2 = jedis.setbit("hello2", 0, true);
        System.out.println(hello2);
    }

    @Test
    public void testBit() {
        Boolean hello2 = jedis.getbit("hello2", 100);
        System.out.println(hello2);
    }

    @Test
    public void testAppend() {
        Long append = jedis.append("hello", "world");
        System.out.println(append);
    }
}
