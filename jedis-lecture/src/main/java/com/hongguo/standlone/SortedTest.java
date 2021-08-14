package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ZAddParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongguo_cheng
 * @date 2021/7/12
 */
public class SortedTest {
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
    public void testZadd1() {
        String key = "sorted-set-1";
        Long a = jedis.zadd(key, 100.0, "b");
        System.out.println(a);

        Map<String, Double> map = new HashMap<>();
        map.put("c", 300.0);
        map.put("d", 99.0);
        map.put("e", 300.01);
        Long zadd = jedis.zadd(key, map);
        System.out.println(zadd);
    }

    @Test
    public void testZadd2() {
        String key = "sorted-set-2";
        Map<String, Double> map = new HashMap<>();
        map.put("a", 10.0);
        map.put("b", 2000.0);
        map.put("c", 10.0);
        Long a = jedis.zadd(key, map, ZAddParams.zAddParams().ch().xx());
        System.out.println(a);
    }

    @Test
    public void testZrem() {
        String key = "sorted-set-2";
        Long a = jedis.zrem(key, "a", "b");
        System.out.println(a);
    }

    @Test
    public void testZscore() {
        String key = "sorted-set-2";
        Double a = jedis.zscore(key, "a");
        System.out.println(a);
    }

    @Test
    public void testZincrby() {
        String key = "sorted-set-2";
        Double a = jedis.zincrby(key, 20.0, "a");
        System.out.println(a);
        a = jedis.zincrby(key, 20.0, "f");
        System.out.println(a);
    }

    @Test
    public void testZcard() {
        String key = "sorted-set-2";
        Long zcard = jedis.zcard(key);
        System.out.println(zcard);
    }
}
