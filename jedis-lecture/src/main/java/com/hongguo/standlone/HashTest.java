package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hongguo_cheng
 * @date 2021/7/10
 */
public class HashTest {

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
    public void testHset() {
        Long title = jedis.hset("hash-key", "title", "redis.io");
        System.out.println(title);
    }

    @Test
    public void testHsetMap() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "h-title");
        map.put("author", "hongguo");
        map.put("content", "hello world");
        Long hset = jedis.hset("article:100", map);
        System.out.println(hset);
    }

    @Test
    public void testHsetNx() {
        Long title = jedis.hsetnx("hash-key", "title", "redis.iooo");
        System.out.println(title);
        Long hsetnx = jedis.hsetnx("hash-key", "author", "hongguo");
        System.out.println(hsetnx);
    }

    @Test
    public void testHincrBy() {
        Long viewCount = jedis.hincrBy("hash-key", "view-count", 100L);
        System.out.println(viewCount);
        viewCount = jedis.hincrBy("hash-key", "view-count", 100L);
        System.out.println(viewCount);

        Long title = jedis.hdel("hash-key", "title");
        System.out.println(title);
    }

    @Test
    public void testHget() {
        String author = jedis.hget("hash-key", "author");
        System.out.println(author);
        Map<String, String> map = jedis.hgetAll("hash-key");
        System.out.println(map);
    }

    @Test
    public void testHmset() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("foo", "bar");
        String hmset = jedis.hmset("hash-mset", map);
        System.out.println(hmset);
    }

    @Test
    public void testHkeys() {
        Set<String> hkeys = jedis.hkeys("hash-key");
        System.out.println(hkeys);
        List<String> hvals = jedis.hvals("hash-key");
        System.out.println(hvals);
        Map<String, String> map = jedis.hgetAll("hash-key");
        System.out.println(map);
    }
}
