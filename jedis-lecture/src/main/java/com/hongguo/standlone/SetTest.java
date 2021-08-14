package com.hongguo.standlone;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author hongguo_cheng
 * @date 2021/7/11
 */
public class SetTest {
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
    public void testSadd() {
        String key = "set1";
        Long sadd = jedis.sadd(key, "a", "b", "c", "a", "e", "c");
        System.out.println(sadd);
    }

    @Test
    public void testSrem() {
        String key = "set1";
        Long a = jedis.srem(key, "a");
        System.out.println(a);
    }

    @Test
    public void testSmove() {
        String source = "set1";
        String target = "set2";
        Long a = jedis.smove(source, target, "c");
        System.out.println(a);
        Long f = jedis.smove(source, target, "f");
        System.out.println(f);
    }

    @Test
    public void testScard() {
        String key = "set2";
        Long scard = jedis.scard(key);
        System.out.println(scard);

        Boolean f = jedis.sismember(key, "c");
        System.out.println(f);
    }

    @Test
    public void testSrandom() {
        String key = "set2";
        jedis.sadd(key, "a", "b", "c", "d", "e", "f");
        List<String> srandmember = jedis.srandmember(key, -3);
        System.out.println(srandmember);
    }

    @Test
    public void testSpop() {
        String key = "set2";
        Set<String> spop = jedis.spop(key, 2);
        System.out.println(spop);
    }

    @Test
    public void testSinter() {
        String set1 = "set1";
        String set2 = "set2";
        Set<String> sinter = jedis.sinter(set1, set2);
        System.out.println(sinter);

        Long set3 = jedis.sinterstore("set3", set1, set2);
        System.out.println(set3);
    }

    @Test
    public void testSunion() {
        String set1 = "set1";
        String set2 = "set2";
        Set<String> sunion = jedis.sunion(set1, set2);
        System.out.println(sunion);

        Long set3 = jedis.sunionstore("set3", set1, set2);
        System.out.println(set3);
    }

    @Test
    public void testSdiff() {
        String set4 = "set4";
        String set5 = "set5";
        Set<String> sdiff = jedis.sdiff(set4, set5);
        System.out.println(sdiff);

        Long set6 = jedis.sdiffstore("set6", set4, set5);
        System.out.println(set6);
    }
}
